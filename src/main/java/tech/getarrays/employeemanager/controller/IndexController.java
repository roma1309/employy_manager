package tech.getarrays.employeemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tech.getarrays.employeemanager.entity.Employee;


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

    @GetMapping("/update")
    public String editForm(Model model) {

        model.addAttribute("employee", new Employee());
        return "views/editProfile";
    }

}
