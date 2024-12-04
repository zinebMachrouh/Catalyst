package org.example.catalyst.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.catalyst.entities.enums.Role;

import java.util.Collection;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
