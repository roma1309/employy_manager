package tech.getarrays.employeemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String shouIndexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String shouLoginForm() {

        return "views/login";
    }

}
