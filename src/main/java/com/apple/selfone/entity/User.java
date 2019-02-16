package com.apple.selfone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    private String activationCode;

    private boolean active;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles = new ArrayList<>();

    public User(String username, String email, String password, String fullName, String activationCode, boolean active, List<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.activationCode = activationCode;
        this.active = active;
        this.roles = roles;
    }

    public User(String username, String email, String password, String fullName, String activationCode, List<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.activationCode = activationCode;
        this.roles = roles;
    }
}
