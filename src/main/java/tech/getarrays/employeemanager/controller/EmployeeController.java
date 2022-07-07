package tech.getarrays.employeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.getarrays.employeemanager.entity.Employee;
import tech.getarrays.employeemanager.service.EmployeeService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public String getAllEmployeesForAdmin(Model model, @RequestParam(defaultValue = "") String name) {
        List<Employee> employees = employeeService.findAllEmployees().
                stream().
                filter(employee -> employee.getName().contains(name)).collect(Collectors.toList());
        model.addAttribute("employees", employees);
        return "views/list";
    }


    @GetMapping("/employees")
    public String getEmployees(Model model, @RequestParam(defaultValue = "") String name) {
        List<Employee> employees = employeeService.findAllEmployees().
                stream().
                filter(employee -> employee.getName().contains(name)).collect(Collectors.toList());
        model.addAttribute("employees", employees);
        return "views/allUsers";
    }

    @PostMapping("/update")
    public String updateEmployee(@RequestParam("name") String name,
                                 @RequestParam("jobTittle") String jobTittle,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("image") MultipartFile file, Principal principal,
                                 Model model) {
        Employee employee = employeeService.findByEmail(principal.getName());
        employeeService.updateEmployee(employee, name, jobTittle, phone, file);
        return "redirect:/myProfile";

    }

    @GetMapping("/{email}/delete")
    public String deleteEmployee(@PathVariable(value = "email") String email) {
        employeeService.deleteEmployee(email);
        return "redirect:/all";
    }
}
