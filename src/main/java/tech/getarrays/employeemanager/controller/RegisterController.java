package tech.getarrays.employeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.service.EmployeeService;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/register")
    public String registerFrom(Model model) {
        model.addAttribute("employee", new Employee());
        return "views/register";
    }

    @PostMapping("/register")
    public String registerEmployee(@Valid Employee employee, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "views/register";
        }
        if (employeeService.isEmployeePresent(employee.getEmail())) {
            model.addAttribute("exist", true);
            return "views/register";
        }
        employeeService.addUser(employee);
        return "views/success";
    }
}
