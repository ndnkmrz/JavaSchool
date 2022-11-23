package com.gamershop.admin.user.service;

import com.gamershop.admin.user.interfaces.IUserService;
import com.gamershop.admin.user.mapper.UserMapper;
import com.gamershop.admin.user.repo.UserRepository;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder, RoleService roleService, UserMapper userMapper){

        this.userRepo = userRepo;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public List<UserDTO> listUsers(){
        var userList = (List<UserEntity>) userRepo.findAll();
        return userList.stream().map(userMapper::toDTO).toList();
    }

    public void saveUser(UserDTO user){
        encodePassword(user);
        UserEntity userEntity = userMapper.toUser(user);
        user.getRoles().stream().map(roleService::getOrCreateRole).forEach(userEntity::addRole);
        userRepo.save(userEntity);

    }

    private void encodePassword(UserDTO user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    /**
     * Checking email uniqueness to display a page to the user in case of an error
     * @param email
     * @return
     */
    public boolean isEmailUnique(String email){
        return userRepo.getUserEntityByUserEmail(email).isPresent();
    }

}
