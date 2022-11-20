package com.gamershop.shared.dto;

import com.gamershop.shared.entity.RoleEntity;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
import java.util.Set;
@Getter
@Setter
public class UserDTO {
    private String userName;
    private String userEmail;
    private String passwordHash;
    private boolean enabled = true;
    private List<String> roles;


    public UserDTO(String userName, String userEmail, String passwordHash, boolean enabled, List<String> roles) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.passwordHash = passwordHash;
        this.enabled = enabled;
        this.roles = roles;
    }
    public UserDTO(){ }
}
