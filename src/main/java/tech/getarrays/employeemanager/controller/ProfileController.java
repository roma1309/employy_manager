package tech.getarrays.employeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.getarrays.employeemanager.entity.Employee;
import tech.getarrays.employeemanager.entity.Task;
import tech.getarrays.employeemanager.service.EmployeeService;
import tech.getarrays.employeemanager.service.TaskService;
import tech.getarrays.employeemanager.utils.DateUtils;

import java.security.Principal;
import java.util.Date;

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

    @GetMapping("/{id}/completed")
    public String deleteTask(@PathVariable(value = "id") Long id, Model model) {
        Task task = taskService.findTask(id);
        if (!DateUtils.getDate(task.getStartDate()).before(new Date())) {
            taskService.deleteTask(id);
            return "redirect:/myProfile";
        } else {
            model.addAttribute("error", "The time of this task has expired");
            return "views/myProfile";
        }

    }
}
