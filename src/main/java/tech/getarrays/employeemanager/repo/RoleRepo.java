package tech.getarrays.employeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.employeemanager.model.Role;

public interface RoleRepo extends JpaRepository<Role, String> {
}
