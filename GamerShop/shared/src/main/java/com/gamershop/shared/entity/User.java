package com.gamershop.shared.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UserID;

    @Column(length = 120, nullable = false, unique = true)
    private String UserEmail;
    private String PasswordHash;
    @Column(length = 50, nullable = false, unique = true)
    private String UserName;

    private boolean Enabled;

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
        UserEmail = userEmail;
        PasswordHash = passwordHash;
        UserName = userName;
        Enabled = enabled;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getPasswordHash() {
        return PasswordHash;
    }

    public void setPasswordHash(String passwordHash) {
        PasswordHash = passwordHash;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean enabled) {
        Enabled = enabled;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

}
