package com.gamershop.admin.user;

import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.RoleEntity;
import com.gamershop.shared.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class Mapper {
    public UserDTO toDTO(UserEntity userEntity){
        String userName = userEntity.getUserName();
        String userEmail = userEntity.getUserEmail();
        String passwordhash = userEntity.getPasswordHash();
        boolean enable = userEntity.isEnabled();
        List<String> roles = userEntity.getRoles().stream().map(RoleEntity::getRoleName).collect(toList());
        return new UserDTO(userName, userEmail, passwordhash, enable, roles);
    }

    public UserEntity toUser(UserDTO userDTO){
        return new UserEntity(userDTO.getUserEmail(), userDTO.getPasswordHash(), userDTO.getUserName(), userDTO.isEnabled(), new ArrayList<>());
    }
}
