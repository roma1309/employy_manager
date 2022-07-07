package tech.getarrays.employeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tech.getarrays.employeemanager.exeption.UserNotFoundExceptional;
import tech.getarrays.employeemanager.entity.Employee;
import tech.getarrays.employeemanager.entity.Role;
import tech.getarrays.employeemanager.repo.EmployeeRepo;


import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

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

        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        employee.setRoles(roles);
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);
    }

    public Employee addAdmin(Employee employee) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        employee.setPassword(encoder.encode(employee.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        employee.setRoles(roles);
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepo.save(employee);

    }

    public Employee findByEmail(String email) {
        return employeeRepo.findByEmail(email);
    }

    public Employee updateEmployee(Employee employee,
                                   String name,
                                   String jobTittle,
                                   String phone,
                                   MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            if (!fileName.equals(" ")) {
                employee.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!name.equals("")) {
            employee.setName(name);
        }
        if (!jobTittle.equals("")) {
            employee.setJobTittle(jobTittle);
        }
        if (!phone.equals("")) {
            employee.setPhone(phone);
        }
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(String email) {
        employeeRepo.deleteByEmail(email);
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
