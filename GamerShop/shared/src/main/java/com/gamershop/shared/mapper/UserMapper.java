package com.gamershop.shared.mapper;

import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.RoleEntity;
import com.gamershop.shared.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserMapper {
    public UserDTO toDTO(UserEntity userEntity){
        Integer id = userEntity.getUserId();
        String userName = userEntity.getUserName();
        String userEmail = userEntity.getUserEmail();
        String passwordhash = userEntity.getPasswordHash();
        boolean enable = userEntity.isEnabled();
        List<String> roles = userEntity.getRoles().stream().map(RoleEntity::getRoleName).collect(toList());
        return new UserDTO(id, userName, userEmail, passwordhash, enable, roles);
    }

    public UserEntity toUser(UserDTO userDTO){
        return new UserEntity(userDTO.getId(), userDTO.getUserEmail(), userDTO.getPassword(), userDTO.getUserName(), userDTO.isEnabled(), new ArrayList<>());
    }
}
