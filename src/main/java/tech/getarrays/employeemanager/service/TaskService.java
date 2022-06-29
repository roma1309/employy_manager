package tech.getarrays.employeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.model.Task;
import tech.getarrays.employeemanager.repo.TaskRepo;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskService {

    private TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task addTask(Task task, Employee employee) {
        task.setEmployee(employee);
        return taskRepo.save(task);
    }

    public List<Task> findEmployeeTask(Employee employee) {
        return taskRepo.findByEmployee(employee);
    }
}
