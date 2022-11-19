package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="User")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(length = 120, nullable = false, unique = true)
    private String userEmail;
    private String passwordHash;
    @Column(length = 50, nullable = false, unique = true)
    private String userName;

    private boolean enabled = true;

    @ManyToMany
    @JoinTable(
            name="UserRoles",
            joinColumns = @JoinColumn(
                    name = "UserID", referencedColumnName = "UserID"),
            inverseJoinColumns = @JoinColumn(
                    name = "RoleID", referencedColumnName = "RoleID"))
    private Set<Role> roles = new HashSet<>();

    public User(){

    }

    public User(String userEmail, String passwordHash, String userName, boolean enabled) {
        this.userEmail = userEmail;
        this.passwordHash = passwordHash;
        this.userName = userName;
        this.enabled = enabled;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

}
