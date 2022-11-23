package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="User")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(length = 120, nullable = false, unique = true)
    private String userEmail;
    private String passwordHash;
    @Column(length = 50, nullable = false)
    private String userName;

    private boolean enabled = true;

    @ManyToMany
    @JoinTable(
            name="UserRoles",
            joinColumns = @JoinColumn(
                    name = "UserID", referencedColumnName = "UserID"),
            inverseJoinColumns = @JoinColumn(
                    name = "RoleID", referencedColumnName = "RoleID"))
    private List<RoleEntity> roles;

    public UserEntity(){ }

    public UserEntity(String userEmail, String passwordHash, String userName, boolean enabled) {
        this.userEmail = userEmail;
        this.passwordHash = passwordHash;
        this.userName = userName;
        this.enabled = enabled;
    }

    public UserEntity(String userEmail, String passwordHash, String userName, boolean enabled, ArrayList<RoleEntity> roles) {
        this.userEmail = userEmail;
        this.passwordHash = passwordHash;
        this.userName = userName;
        this.enabled = enabled;
        this.roles = roles;
    }

    public void addRole(RoleEntity role){
        this.roles.add(role);
    }

}
