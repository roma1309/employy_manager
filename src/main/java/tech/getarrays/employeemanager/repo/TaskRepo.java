package tech.getarrays.employeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.employeemanager.entity.Employee;
import tech.getarrays.employeemanager.entity.Task;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByEmployee(Employee employee);

}
