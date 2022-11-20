package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Roles")
@Getter
@Setter
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    @Column(length = 40, nullable = false, unique = true)
    private String roleName;

//    @ManyToMany(mappedBy = "Roles")
//    private Set<User> Users = new HashSet<>();

    public RoleEntity(){  }
    public RoleEntity(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString(){
        return roleName;
    }

}
