package org.example.springsecurity.entity;
import jakarta.persistence.*;
import lombok.*;
import org.example.springsecurity.entity.Permission;

import java.util.*;
@Entity @Table(name="roles", uniqueConstraints=@UniqueConstraint(columnNames="name"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false, unique=true) private String name;
 @ManyToMany(fetch=FetchType.EAGER)
 @JoinTable(name="role_permissions", joinColumns=@JoinColumn(name="role_id"), inverseJoinColumns=@JoinColumn(name="permission_id"))
 @Builder.Default private Set<Permission> permissions = new HashSet<>();
}
