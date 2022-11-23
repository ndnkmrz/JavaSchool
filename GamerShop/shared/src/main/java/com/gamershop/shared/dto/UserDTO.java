package com.gamershop.shared.dto;

import lombok.*;


import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String userName;
    private String userEmail;
    private String password;
    private boolean enabled = true;
    private List<String> roles;

}
