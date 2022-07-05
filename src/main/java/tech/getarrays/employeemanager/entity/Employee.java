package tech.getarrays.employeemanager.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Email
    @Column(unique = true)
    @NotEmpty
    private String email;
    private String phone;
    @Size(min = 6)
    private String password;
    private String jobTittle;
    @Column(nullable = false, updatable = false)
    private String employeeCode;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Task> tasks;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "employee_roles", joinColumns = {
            @JoinColumn(name = "employee_email", referencedColumnName = "email")
    }, inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")})
    private List<Role> roles;

    public Employee() {
    }

    public Employee(String name, String email, String phone, String password, String jobTittle) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.jobTittle = jobTittle;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTittle() {
        return jobTittle;
    }

    public void setJobTittle(String jobTittle) {
        this.jobTittle = jobTittle;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", jobTittle='" + jobTittle + '\'' +
                ", employeeCode='" + employeeCode + '\'' +
                '}';
    }
}
