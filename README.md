# Project EMPLOYEE MANAGER

THE EMPLOYEE MANAGER *is an application for company employees that has two roles (Administrator and User).
The administrator can dismiss employees and give them tasks.
The user can see all employees and complete tasks.*

## Installation
1. Clone the repository from githab.
2. Create a database employee_manager in MySQL. 
3. In the src/main/resources/application.properties file, change the password and username to your own
```
spring.datasource.username=username
spring.datasource.password=password
```
4.To register as an admin or user, you need to change the methods in src/main/java/tech/getarrays/employeemanager/controller/RegisterController.java
```
  @PostMapping("/register")
    public String registerEmployee(@Valid Employee employee, BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            return "views/register";
        }
        if (employeeService.isEmployeePresent(employee.getEmail())) {
            model.addAttribute("exist", true);
            return "views/register";
        }
        employeeService.addAdmin(employee); or  employeeService.addUser(employee);
        return "views/success";
    }
```