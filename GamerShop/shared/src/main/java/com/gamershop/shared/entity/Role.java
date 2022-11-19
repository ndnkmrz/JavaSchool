package com.gamershop.shared.entity;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer RoleID;
    @Column(length = 40, nullable = false, unique = true)
    private String RoleName;

//    @ManyToMany(mappedBy = "Roles")
//    private Set<User> Users = new HashSet<>();

    public Role(){  }
    public Role(String roleName) {
        RoleName = roleName;
    }

    public Integer getRoleID() {
        return RoleID;
    }

    public void setRoleID(Integer roleID) {
        RoleID = roleID;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    @Override
    public String toString(){
        return RoleName;
    }

}
