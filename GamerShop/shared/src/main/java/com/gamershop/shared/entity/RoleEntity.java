package com.gamershop.shared.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Roles")
@Getter
@Setter
@NoArgsConstructor
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;
    @Column(length = 40, nullable = false, unique = true)
    private String roleName;

    public RoleEntity(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString(){
        return roleName;
    }

}
