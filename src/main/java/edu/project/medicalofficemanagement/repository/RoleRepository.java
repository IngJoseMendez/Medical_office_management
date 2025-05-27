package edu.project.medicalofficemanagement.repository;

import edu.project.medicalofficemanagement.model.Role;
import edu.project.medicalofficemanagement.enums.role.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Roles name);
}
