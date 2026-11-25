package com.arosetu.backend.controller;

import com.arosetu.backend.dto.UserResponse;
import com.arosetu.backend.entity.User;
import com.arosetu.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication auth) {
        User user = userService.getUserEntityByEmail(auth.getName());
        return ResponseEntity.ok(mapToResponse(user));
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateCurrentUser(Authentication auth, @RequestBody Map<String, String> updates) {
        User user = userService.getUserEntityByEmail(auth.getName());
        User updateData = new User();
        updateData.setName(updates.getOrDefault("name", user.getName()));
        updateData.setPhone(updates.getOrDefault("phone", user.getPhone()));
        User updated = userService.updateUser(user.getId(), updateData);
        return ResponseEntity.ok(mapToResponse(updated));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole()
        );
    }
}
