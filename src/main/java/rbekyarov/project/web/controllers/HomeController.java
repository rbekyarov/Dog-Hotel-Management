package rbekyarov.project.web.controllers;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import rbekyarov.project.service.UserService;
import rbekyarov.project.service.*;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController extends BaseController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView, HttpSession session) {
        Object user = session.getAttribute("user");
        Object admin = session.getAttribute("admin");

        if((admin !=null) ||(user!=null)){
            modelAndView.setViewName("/view/home");
        }else {
            modelAndView.setViewName("/index");
        }
        String viewName = modelAndView.getViewName();
        return super.view(viewName);
    }
}