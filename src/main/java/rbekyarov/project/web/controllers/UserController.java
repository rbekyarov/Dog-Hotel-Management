package rbekyarov.project.web.controllers;

import javassist.tools.rmi.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rbekyarov.project.models.dto.UserDTO;
import rbekyarov.project.models.dto.UserEditDTO;
import rbekyarov.project.models.dto.UserLoginDTO;
import rbekyarov.project.models.dto.UserRegisterDTO;
import rbekyarov.project.models.entity.Behavior;
import rbekyarov.project.models.entity.User;
import rbekyarov.project.models.entity.enums.Role;
import rbekyarov.project.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


        return super.view("/view/add/userAdd", "userDTO", userDTO);

    }

    @PostMapping("/view/add/userAdd")
    public String addUser(@Valid UserDTO userDTO) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords don't match!");
        }
        userService.addUser(userDTO);

        return "redirect:/view/table/userTable";
    }

    @GetMapping("/view/register")
    public ModelAndView register(ModelAndView modelAndView, HttpSession session) {
        UserDTO userDTO = new UserDTO();

        modelAndView.addObject("userDTO", userDTO);
        Object user = session.getAttribute("username");
        if (user != null) {
            modelAndView.setViewName("/view/home");
        } else {
            modelAndView.setViewName("/view/register");
        }

        return super.view(modelAndView.getViewName(), "userDTO", userDTO);
    }

    @PostMapping("/view/register")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterDTO userRegisterDTO,
                                        ModelAndView modelAndView) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords don't match!");
        }
        String username = userRegisterDTO.getUsername();
        Optional<User> byUsername = userService.findByUsername(username);
        if (byUsername==null) {
            userService.registerUser(userRegisterDTO);
            modelAndView.setViewName("redirect:/view/login");
        } else {
            modelAndView.setViewName("redirect:/view/register");
        }


        return modelAndView;
    }

    @GetMapping("/view/login")
    public ModelAndView login(ModelAndView modelAndView, HttpSession session) {
        Object user = session.getAttribute("username");
        if (user != null) {
            modelAndView.setViewName("/view/home");
        } else {
            user = new User();
            modelAndView.setViewName("/view/login");
        }

        return super.view(modelAndView.getViewName(), "user", user);
    }

    @PostMapping("/view/login")
    public ModelAndView loginConfirm(@ModelAttribute UserLoginDTO userLoginDTO, ModelAndView modelAndView,
                                     HttpSession session) {
        UserDTO userDTO = this.modelMapper.map(userLoginDTO, UserDTO.class);
        UserDTO userLogin = this.userService.loginUser(userDTO);

        if (userLogin == null) {
            modelAndView.setViewName("/index");

        } else {
            User user = new User();
            Long id = userLogin.getId();
            String username = userLogin.getUsername();
            Role role = userLogin.getRole();
            user.setId(id);
            user.setUsername(username);
            user.setRole(role);

            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            if (user.getRole().name().equals("ADMIN")) {
                session.setAttribute("admin", user.getRole());
                modelAndView.setViewName("/view/home");

            } else {
                session.setAttribute("user", user.getRole());
                modelAndView.setViewName("/view/home");
            }

        }


        return super.view(modelAndView.getViewName(), "session", session);

    }

    @GetMapping("/view/home")
    public ModelAndView home(ModelAndView modelAndView, HttpSession session) {
        Object user = session.getAttribute("username");

        if (user != null) {
            modelAndView.setViewName("/view/home");
        } else {
            modelAndView.setViewName("/view/login");
        }


        return super.view(modelAndView.getViewName(), "user", user);
    }


    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute("username") != null) {
            session.invalidate();
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.setViewName("redirect:/view/login");
        }

        return modelAndView;
    }

    @GetMapping("/view/table/userTable")
    public ModelAndView userTable(ModelAndView modelAndView, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);

        Page<User> users = userService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        int totalPages = users.getTotalPages();
        List<Integer> pageNumbers = null;
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }
        modelAndView.addObject("users", users);
        return super.view("/view/table/userTable", "users", users,"pageNumbers", pageNumbers);
    }

    @GetMapping("view/table/user/edit/{id}")
    public ModelAndView getUserDetail(@PathVariable("id") Long id,
                                      ModelAndView modelAndView) throws ObjectNotFoundException {

        User userDto =
                userService.findById(id).
                        orElseThrow(() -> new ObjectNotFoundException("not found!"));

        modelAndView.addObject("userDto", userDto);

        return super.view("/view/edit/userEdit", "userDto", userDto);

    }

    @PostMapping("view/table/user/edit/{id}/edit")
    public String editBehavior(@PathVariable("id") Long id, UserEditDTO userEditDTO) throws ObjectNotFoundException {

        userService.editUser(userEditDTO.getRole(), id);

        return "redirect:/view/table/userTable";
    }

    @GetMapping("view/table/user/remove/{id}")
    public String removeUser(@PathVariable Long id) {
        userService.removeUserById(id);

        return "redirect:/view/table/userTable";
    }
}
