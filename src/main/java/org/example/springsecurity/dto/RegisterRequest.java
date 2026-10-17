package org.example.springsecurity.dto;
import jakarta.validation.constraints.*;
public record RegisterRequest(@NotBlank @Size(max=120) String fullName,
 @NotBlank @Email @Size(max=180) String email,
 @NotBlank @Size(min=8,max=72) String password,
 @NotBlank @Size(max=30) String phone) {}
