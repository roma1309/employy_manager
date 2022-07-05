package tech.getarrays.employeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.employeemanager.entity.Employee;
import tech.getarrays.employeemanager.service.EmployeeService;

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

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @GetMapping("/{email}/delete")
    public String deleteEmployee(@PathVariable(value = "email") String email) {
        employeeService.deleteEmployee(email);
        return "redirect:/all";
    }
}
