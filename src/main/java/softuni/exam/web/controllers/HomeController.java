package softuni.exam.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.exam.service.*;

@Controller
public class HomeController extends BaseController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    @GetMapping("/")
    public ModelAndView index() {

        return super.view("index");
    }
}
