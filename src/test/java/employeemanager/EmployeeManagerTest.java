package employeemanager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tech.getarrays.employeemanager.EmployeeManager;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.model.Task;
import tech.getarrays.employeemanager.service.EmployeeService;
import tech.getarrays.employeemanager.service.TaskService;

import java.util.List;



@RunWith(SpringRunner.class)
@AutoConfigureDataJpa
@AutoConfigureTestEntityManager
public class EmployeeManagerTest {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private TaskService taskService;


    @Before
    public void initDB() {
        {
            Employee newEmployee = new Employee("roma", "roma@mail.ru", "12341234", "12345678", "june");
            employeeService.addUser(newEmployee);
        }

        {
            Employee newEmployee = new Employee("vanya", "vanya@mail.ru", "000000", "12345678", "june");
            employeeService.addUser(newEmployee);
        }
        Task task = new Task("11/12/2022", "14:11", "15:14", "You need to work today");
        Employee employee = employeeService.findByEmail("vanya@mail.ru");
        taskService.addTask(task, employee);
    }

    @Test
    public void testEmployee() {
        Employee employee = employeeService.findByEmail("vanya@mail.ru");
        Employee employeeAdmin = employeeService.findByEmail("vanya@mail.ru");
        Assert.assertNotNull(employee);
        Assert.assertEquals(employeeAdmin.getEmail(), "vanya@mail.ru");
    }

    @Test
    public void testTask() {
        Employee employee = employeeService.findByEmail("vanya@mail.ru");
        List<Task> tasks = taskService.findEmployeeTask(employee);
        Assert.assertNotNull(employee);
    }

}
