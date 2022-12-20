package com.gamershop.admin.user.interfaces;

import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.shared.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserService {
    List<UserDTO> listUsers();
    Page<UserDTO> listByPage(int pageNum, String sortField, String sortDir, String keyword);
    void saveUser(UserDTO user);
    UserDTO getUserById(Integer id);
    UserDTO getUserByEmail(String email);
    boolean isEmailUnique(String email);

}
