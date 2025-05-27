package edu.project.medicalofficemanagement.service;

import edu.project.medicalofficemanagement.dto.auth.RegisterRequest;
import edu.project.medicalofficemanagement.model.User;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Page<User> pageUsers(Pageable pageable);

    void register(RegisterRequest registerRequest) throws Exception;

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    void updateUser(Long id, User userDetails) throws Exception;

    void deleteUser(Long id) throws Exception;

}
