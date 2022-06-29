package tech.getarrays.employeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.exeption.UserNotFoundExceptional;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.model.Role;
import tech.getarrays.employeemanager.repo.EmployeeRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {
    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addUser(Employee employee) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        employee.setPassword(encoder.encode(employee.getPassword()));
        Role role = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        employee.setRoles(roles);
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public Employee addAdmin(Employee employee) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        employee.setPassword(encoder.encode(employee.getPassword()));
        Role role = new Role("ADMIN");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        employee.setRoles(roles);
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public Employee findByEmail(String email) {
        return employeeRepo.findByEmail(email);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteEmployeeById(id);
    }


    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id).
                orElseThrow(() -> new UserNotFoundExceptional("User by id " + id + " not found"));
    }

    public boolean isEmployeePresent(String email) {
        Employee employee = employeeRepo.findByEmail(email);
        if (employee != null) {
            return true;
        }
        return false;
    }
}
