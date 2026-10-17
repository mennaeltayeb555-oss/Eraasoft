package org.example.springsecurity.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name="permissions", uniqueConstraints=@UniqueConstraint(columnNames="name"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Permission {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false, unique=true) private String name;
}
