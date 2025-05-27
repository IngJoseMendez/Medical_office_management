package edu.project.medicalofficemanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.project.medicalofficemanagement.dto.UserResponseDTO;
import edu.project.medicalofficemanagement.dto.auth.RegisterRequest;
import edu.project.medicalofficemanagement.model.User;
import edu.project.medicalofficemanagement.security.service.JpaUserDetailService;
import edu.project.medicalofficemanagement.security.jwt.JwtUtil;
import edu.project.medicalofficemanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JpaUserDetailService userDetailsSvc;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        try {
            userService.register(req);
            return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        } catch (Exception ex) {
            LOG.error("register error", ex);
            return ResponseEntity.badRequest()
                    .body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body) {
        String user = body.get("username"), pass = body.get("password");
        if (user == null || pass == null)
            return ResponseEntity.badRequest().body(Map.of("error","username/password missing"));
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user, pass));
            UserDetails ud = userDetailsSvc.loadUserByUsername(user);
            String token = jwtUtil.generateToken(ud.getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception ex) {
            LOG.error("login error", ex);
            return ResponseEntity.status(401)
                    .body(Map.of("error","Invalid credentials"));
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> all(
            @RequestParam(name = "range", required = false) String rangeJson,
            @RequestParam(name = "sort", required = false) String sortJson,
            @RequestParam(name = "filter", required = false) String filterJson
    ) {
        ObjectMapper mapper = new ObjectMapper();

        int start = 0;
        int end = 9;

        try {
            if (rangeJson != null) {
                int[] range = mapper.readValue(rangeJson, int[].class);
                start = range[0];
                end = range[1];
            }
        } catch (Exception e) {
            // ignorar
        }

        // Sort y filtro (igual que tienes)

        List<User> fullList = userService.getAllUsers();

        // Ordenamiento (igual)

        int total = fullList.size();

        if (start < 0) start = 0;
        if (end >= total) end = total - 1;
        if (start > end) start = 0;

        List<User> pagedList = fullList.subList(start, end + 1);

        List<UserResponseDTO> dtoList = pagedList.stream().map(user -> {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setFirstName(user.getFirstName());
            dto.setLastName(user.getLastName());
            dto.setRoles(user.getRoles().stream()
                    .map(role -> role.getName().name())
                    .collect(Collectors.toSet()));
            return dto;
        }).collect(Collectors.toList());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Range", "users " + start + "-" + end + "/" + total);
        headers.add("Access-Control-Expose-Headers", "Content-Range");

        return ResponseEntity.ok()
                .headers(headers)
                .body(dtoList);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return userService.getUserById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody User det) {
        try {
            userService.updateUser(id, det);
            return ResponseEntity.ok(Map.of("message","User updated"));
        } catch (Exception ex) {
            LOG.error("update error", ex);
            return ResponseEntity.badRequest().body(Map.of("error",ex.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(Map.of("message","User deleted"));
        } catch (Exception ex) {
            LOG.error("delete error", ex);
            return ResponseEntity.notFound().build();
        }
    }
}
