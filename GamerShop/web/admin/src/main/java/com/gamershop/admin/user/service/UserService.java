package com.gamershop.admin.user.service;

import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.admin.user.interfaces.IUserService;
import com.gamershop.shared.mapper.UserMapper;
import com.gamershop.admin.user.repo.UserRepository;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {
    public static final int USERS_PER_PAGE = 5;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

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

    public Page<UserDTO> listByPage(int pageNum,
                                    String sortField,
                                    String sortDir,
                                    String keyword){
        Page<UserEntity> userEntities;
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNum -1, USERS_PER_PAGE, sort);
        if (keyword != null){
            keyword = "%" + keyword + "%";
            userEntities = userRepo.findAll(keyword, pageable);
        }
        else {
            userEntities = userRepo.findAll(pageable);
        }
        List<UserDTO> users = userEntities.stream().map(userMapper::toDTO).toList();
        return new PageImpl<>(users, pageable, userEntities.getTotalElements());

    }
    @Transactional
    public void saveUser(UserDTO user){
        LOGGER.debug("Trying to save user");
        if (user.getId() != null){
            UserDTO existingUser = getUserById(user.getId());
            if (user.getPassword().isEmpty()){
                user.setPassword(existingUser.getPassword());
            }
            else {
                encodePassword(user);
            }
            if (user.getRoles() == null){
                user.setRoles(existingUser.getRoles());
            }
        }
        else {
            encodePassword(user);
        }
        UserEntity userEntity = userMapper.toUser(user);
        user.getRoles().stream().map(roleService::getOrCreateRole).forEach(userEntity::addRole);
        userRepo.save(userEntity);
    }

    public UserDTO getUserById(Integer id){
        UserEntity user = userRepo.findById(id)
                .orElseThrow(()-> new UserNotFoundException("Could not find any user with ID " + id));
        return userMapper.toDTO(user);
    }

    public UserDTO getUserByEmail(String email){
        UserEntity user = userRepo.getUserEntityByUserEmail(email)
                .orElseThrow(()-> new UserNotFoundException("Could not find any user with: " + email));
        return userMapper.toDTO(user);
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
