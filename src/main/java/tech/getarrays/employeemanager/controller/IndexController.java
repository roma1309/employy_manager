package tech.getarrays.employeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tech.getarrays.employeemanager.service.EmployeeService;
import tech.getarrays.employeemanager.service.TaskService;


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
