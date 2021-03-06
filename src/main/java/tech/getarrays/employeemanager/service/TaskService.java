package tech.getarrays.employeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.entity.Employee;
import tech.getarrays.employeemanager.entity.Task;
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

    public Task findTask(Long id) {
        return taskRepo.findById(id).orElseThrow();
    }

    public void deleteTask(Long id) {
        taskRepo.removeById(id);
    }

    public List<Task> findEmployeeTask(Employee employee) {
        return taskRepo.findByEmployee(employee);
    }
}
