package org.example.task11.user.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {

    @NotBlank(message = "Name must not be null or empty")
    @Size(min = 8, message = "Name length must be greater than 7 characters")
    private String name;

    @Min(value = 18, message = "Age must be 18")
    @Max(value = 18, message = "Age must be 18")
    private Integer age;

    @NotBlank(message = "Password must not be null or empty")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).*$",
            message = "Password must contain uppercase, lowercase, a number, and a special character"
    )
    private String password;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}