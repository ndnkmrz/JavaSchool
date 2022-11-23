package com.gamershop.admin.user.interfaces;

import com.gamershop.shared.dto.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> listUsers();
    void saveUser(UserDTO user);
    boolean isEmailUnique(String email);
}
