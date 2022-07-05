package tech.getarrays.employeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tech.getarrays.employeemanager.entity.Employee;
import tech.getarrays.employeemanager.service.EmployeeService;
import tech.getarrays.employeemanager.service.TaskService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ProfileController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TaskService taskService;

    @GetMapping("/myProfile")
    public String showProfilePage(Model model, Principal principal) {
        String email = principal.getName();
        Employee employee = employeeService.findByEmail(email);
        model.addAttribute("employee", employee);
        model.addAttribute("tasks", taskService.findEmployeeTask(employee));
        return "views/myProfile";
    }
}
