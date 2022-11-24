package com.gamershop.admin.user.interfaces;

import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.shared.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    List<UserDTO> listUsers();
    Page<UserDTO> listByPage(int pageNum);
    void saveUser(UserDTO user) throws UserNotFoundException;
    UserDTO getUser(Integer id) throws UserNotFoundException;
    void deleteUser(Integer id) throws UserNotFoundException;
    boolean isEmailUnique(String email);
}
