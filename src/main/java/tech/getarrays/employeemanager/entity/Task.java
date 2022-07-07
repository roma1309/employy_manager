package tech.getarrays.employeemanager.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String startDate;
    @NotEmpty
    private String stopDate;
    @NotEmpty
    @Size(min = 6)
    private String description;
    @ManyToOne
    @JoinColumn(name = "employee_email")
    private Employee employee;

    public Task() {
    }

    public Task(@NotEmpty String startDate, @NotEmpty String stopDate, @NotEmpty @Size(min = 6) String description, Employee employee) {
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.description = description;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", stopDate='" + stopDate + '\'' +
                ", description='" + description + '\'' +
                ", employee=" + employee +
                '}';
    }
}
