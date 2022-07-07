package tech.getarrays.employeemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import tech.getarrays.employeemanager.entity.Task;
import tech.getarrays.employeemanager.service.EmployeeService;
import tech.getarrays.employeemanager.service.TaskService;
import tech.getarrays.employeemanager.utils.DateUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/addTask")
    public String taskForm(String email, Model model, HttpSession httpSession) {
        httpSession.setAttribute("email", email);
        model.addAttribute("task", new Task());
        return "views/taskForm";
    }

    @PostMapping("/addTask")
    public String addTask(@Valid Task task, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "views/taskForm";
        }

        Date startDate = DateUtils.getDate(task.getStartDate());
        Date stopDate = DateUtils.getDate(task.getStopDate());

        if (startDate.before(new Date()) || stopDate.before(startDate)) {
            model.addAttribute("exist", true);
            return "views/taskForm";
        }

        String email = (String) session.getAttribute("email");
        taskService.addTask(task, employeeService.findByEmail(email));

        return "redirect:/all";
    }
}
