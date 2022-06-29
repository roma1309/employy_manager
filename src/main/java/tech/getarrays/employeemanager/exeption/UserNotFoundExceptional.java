package tech.getarrays.employeemanager.exeption;

public class UserNotFoundExceptional extends RuntimeException {
    public UserNotFoundExceptional(String message) {
        super(message);
    }
}
