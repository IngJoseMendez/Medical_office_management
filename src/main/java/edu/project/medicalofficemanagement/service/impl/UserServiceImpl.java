package edu.project.medicalofficemanagement.service.impl;

import edu.project.medicalofficemanagement.dto.auth.RegisterRequest;
import edu.project.medicalofficemanagement.model.Role;
import edu.project.medicalofficemanagement.model.User;
import edu.project.medicalofficemanagement.repository.RoleRepository;
import edu.project.medicalofficemanagement.repository.UserRepository;
import edu.project.medicalofficemanagement.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepo,
                           RoleRepository roleRepo,
                           PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.encoder = encoder;
    }

    @Override
    public Page<User> pageUsers(Pageable pageable) {
        return userRepo.findAll((org.springframework.data.domain.Pageable) pageable);
    }

    @Override
    public void register(RegisterRequest req) throws Exception {
        if (userRepo.existsByUsername(req.getUsername()))
            throw new Exception("Username already taken");
        if (userRepo.existsByEmail(req.getEmail()))
            throw new Exception("Email already in use");

        User u = new User();
        u.setUsername(req.getUsername());
        u.setEmail(req.getEmail());
        u.setFirstName(req.getFirstName());
        u.setLastName(req.getLastName());
        u.setPassword(encoder.encode(req.getPassword()));

        Role rol = roleRepo.findByName(req.getRole())
                .orElseThrow(() -> new Exception("Role " + req.getRole() + " not found"));
        u.addRole(rol);

        userRepo.save(u);
    }

    @Override public List<User> getAllUsers()                 { return userRepo.findAll(); }
    @Override public Optional<User> getUserById(Long id)      { return userRepo.findById(id); }
    @Override
    public void updateUser(Long id, User det) throws Exception {
        User u = userRepo.findById(id).orElseThrow(() -> new Exception("User not found"));
        u.setFirstName(det.getFirstName());
        u.setLastName(det.getLastName());
        u.setEmail(det.getEmail());
        if (det.getPassword()!=null && !det.getPassword().isEmpty())
            u.setPassword(encoder.encode(det.getPassword()));
        userRepo.save(u);
    }
    @Override
    public void deleteUser(Long id) throws Exception {
        if (!userRepo.existsById(id)) throw new Exception("User not found");
        userRepo.deleteById(id);
    }

}
