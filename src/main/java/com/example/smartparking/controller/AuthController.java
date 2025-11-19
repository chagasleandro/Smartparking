package com.example.smartparking.controller;

import com.example.smartparking.config.JwtUtils;
import com.example.smartparking.entity.User;
import com.example.smartparking.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> body) {
        String username = body.get("username");
        String password = body.get("password");
        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error","user exists"));
        }
        User u = new User();
        u.setUsername(username);
        u.setPassword(passwordEncoder.encode(password));
        u.setRole("ROLE_USER");
        userRepository.save(u);
        return ResponseEntity.ok(Map.of("msg","registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> body) {
        String username = body.get("username");
        String password = body.get("password");
        var opt = userRepository.findByUsername(username);
        if (opt.isEmpty()) return ResponseEntity.status(401).build();
        User u = opt.get();
        if (!passwordEncoder.matches(password, u.getPassword())) return ResponseEntity.status(401).build();
        String token = jwtUtils.generateToken(username);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
