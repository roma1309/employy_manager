package tech.getarrays.employeemanager.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<Employee> employees;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }
}
