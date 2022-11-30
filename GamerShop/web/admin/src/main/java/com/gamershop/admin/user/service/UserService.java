package com.gamershop.admin.user.service;

import com.gamershop.admin.exception.UserNotFoundException;
import com.gamershop.admin.user.interfaces.IUserService;
import com.gamershop.admin.user.mapper.UserMapper;
import com.gamershop.admin.user.repo.UserRepository;
import com.gamershop.shared.dto.UserDTO;
import com.gamershop.shared.entity.UserEntity;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService {
    public static final int USERS_PER_PAGE = 5;
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

    public void saveUser(UserDTO user) throws UserNotFoundException{
        if (user.getId() != null){
            if (user.getPassword().isEmpty()){
                UserDTO existingUser = getUser(user.getId());
                user.setPassword(existingUser.getPassword());
            }
            else {
                encodePassword(user);
            }
        }
        else {
            encodePassword(user);
        }
        UserEntity userEntity = userMapper.toUser(user);
        user.getRoles().stream().map(roleService::getOrCreateRole).forEach(userEntity::addRole);
        userRepo.save(userEntity);
    }

    public UserDTO getUser(Integer id) throws UserNotFoundException{
        UserEntity user = userRepo.findById(id).orElseThrow(()-> new UserNotFoundException("Could not find any user with ID " + id));
        return userMapper.toDTO(user);
    }

    public void deleteUser(Integer id) throws UserNotFoundException{
        if (userRepo.existsById(id)){
            userRepo.deleteById(id);
        }
        else {
            throw new UserNotFoundException("Could not find any user with ID " + id);
        }
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
