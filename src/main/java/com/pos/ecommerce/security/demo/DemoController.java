package com.pos.ecommerce.security.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class DemoController {

    // Any authenticated user
    @GetMapping("/user")
    public ResponseEntity<String> userEndpoint() {
        return ResponseEntity.ok("Hello, authenticated user!");
    }

    // Role-based
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminEndpoint() {
        return ResponseEntity.ok("Hello, Admin!");
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<String> managerEndpoint() {
        return ResponseEntity.ok("Hello, Manager!");
    }

    // Permission-based
    @GetMapping("/admin/users")
    @PreAuthorize("hasAuthority('ADMIN_READ')")
    public ResponseEntity<String> readUsers() {
        return ResponseEntity.ok("User list");
    }

    @DeleteMapping("/admin/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN_DELETE')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

    // Access own data only (SpEL expression)
    @GetMapping("/profile/{id}")
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<String> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok("Profile for user: " + id);
    }
}