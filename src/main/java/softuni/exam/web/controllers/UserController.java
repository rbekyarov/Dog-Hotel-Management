package softuni.exam.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.models.dto.*;
import softuni.exam.models.entity.Behavior;
import softuni.exam.models.entity.User;
import softuni.exam.models.entity.enums.Role;
import softuni.exam.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController extends BaseController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/view/add/userAdd")
    public ModelAndView userAdd(ModelAndView modelAndView) {
        UserDTO userDTO = new UserDTO();

        modelAndView.addObject("userDTO", userDTO);


        return super.view("/view/add/userAdd", "userDTO",userDTO);

    }

    @PostMapping("/view/add/userAdd")
    public String addUser(@Valid UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords don't match!");
        }
        userService.addUser(userDTO);

        return "redirect:/view/table/userTable";
    }

    @GetMapping("/view/register")
    public ModelAndView register(ModelAndView modelAndView, HttpSession session){
        UserDTO userDTO = new UserDTO();

        modelAndView.addObject("userDTO", userDTO);
        Object user = session.getAttribute("username");
        if ( user != null){
            modelAndView.setViewName("/view/home");
        }else {
            modelAndView.setViewName("/view/register");
        }

        return super.view(modelAndView.getViewName(), "userDTO",userDTO);
    }

    @PostMapping("/view/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterDTO model,
                                        ModelAndView modelAndView){
        if (!model.getPassword().equals(model.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords don't match!");
        }

        UserDTO serviceModel = this.modelMapper.map(model, UserDTO.class);
        if (!this.userService.registerUser(serviceModel)){
            throw new IllegalArgumentException("UserRegistration failed!");
        }

        modelAndView.setViewName("redirect:/view/login");
        return modelAndView;
    }

    @GetMapping("/view/login")
    public ModelAndView login(ModelAndView modelAndView, HttpSession session){
        Object user = session.getAttribute("username");
        if (user != null){
            modelAndView.setViewName("/view/home");
        }else {
            user = new User();
            modelAndView.setViewName("/view/login");
        }

        return super.view(modelAndView.getViewName(), "user",user);
    }
    @PostMapping("/view/login")
    public ModelAndView loginConfirm( @ModelAttribute UserLoginDTO userLoginDTO,ModelAndView modelAndView,
                                HttpSession session){
        UserDTO userDTO = this.modelMapper.map(userLoginDTO, UserDTO.class);
        UserDTO userLogin = this.userService.loginUser(userDTO);

        if (userLogin == null){
            modelAndView.setViewName("/view/login");

        }else {
            User user = new User();
            Long id = userLogin.getId();
            String username = userLogin.getUsername();
            Role role = userLogin.getRole();
            user.setId(id);
            user.setUsername(username);
            user.setRole(role);
            modelAndView.setViewName("/view/home");
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

        }


        return super.view(modelAndView.getViewName(), "session",session);

    }
    @GetMapping("/view/home")
    public ModelAndView  home(ModelAndView modelAndView, HttpSession session){
        Object user = session.getAttribute("username");

        if (user !=null){
            modelAndView.setViewName("/view/home");
        }else {
            modelAndView.setViewName("/view/login");
        }


        return super.view(modelAndView.getViewName(), "user",user);
    }



    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session){
        if (session.getAttribute("username") != null){
            session.invalidate();
            modelAndView.setViewName("redirect:/");
        }else {
            modelAndView.setViewName("redirect:/view/login");
        }

        return modelAndView;
    }

    @GetMapping("/view/table/userTable")
    public ModelAndView userTable(ModelAndView modelAndView) {

        List<User> users = userService.findAllUserById();
        modelAndView.addObject("users", users);
        return super.view("/view/table/userTable", "users", users);
    }

    @GetMapping("view/table/user/edit/{id}")
    public ModelAndView getUserDetail(@PathVariable("id") Long id,
                                          ModelAndView modelAndView) throws ObjectNotFoundException {

        var userDto =
                userService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));

        modelAndView.addObject("userDto", userDto);

        return super.view("/view/edit/userEdit", "userDto",userDto);

    }
    @PostMapping("view/table/user/edit/{id}/edit")
    public String editBehavior( @PathVariable("id") Long id , UserEditDTO userEditDTO) throws ObjectNotFoundException {
        var userDto =
                userService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));
        userService.editUser(userEditDTO.getRole(),id);

        return "redirect:/view/table/userTable";
    }

    @GetMapping("view/table/user/remove/{id}")
    public String removeUser( @PathVariable Long id) {
        userService.removeUserById(id);

        return "redirect:/view/table/userTable";
    }
}
