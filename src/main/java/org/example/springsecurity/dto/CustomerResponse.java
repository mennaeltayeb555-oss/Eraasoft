package org.example.springsecurity.dto;
import java.time.LocalDateTime;
import java.util.Set;
public record CustomerResponse(Long id, String fullName, String email, String phone, boolean enabled, Set<String> roles, LocalDateTime createdAt) {}
