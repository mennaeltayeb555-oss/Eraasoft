package org.example.springsecurity.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.*;
@Entity @Table(name="customers", uniqueConstraints=@UniqueConstraint(columnNames="email"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String fullName;
 @Column(nullable=false, unique=true) private String email;
 @Column(nullable=false) private String password;
 @Column(nullable=false) private String phone;
 @Column(nullable=false) @Builder.Default private boolean enabled = true;
 @Column(nullable=false) @Builder.Default private boolean accountNonLocked = true;
 @Column(nullable=false, updatable=false) @Builder.Default private LocalDateTime createdAt = LocalDateTime.now();
 @ManyToMany(fetch=FetchType.EAGER)
 @JoinTable(name="customer_roles", joinColumns=@JoinColumn(name="customer_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
 @Builder.Default private Set<Role> roles = new HashSet<>();
}
