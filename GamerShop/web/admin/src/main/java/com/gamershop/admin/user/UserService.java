package com.gamershop.admin.user;

import com.gamershop.shared.entity.RoleEntity;
import com.gamershop.shared.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder passwordEncoder){

        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserEntity> listUsers(){

        return (List<UserEntity>) userRepo.findAll();
    }

    public Iterable<RoleEntity> listRoles(){
        return roleRepo.findAll();
    }

    public void saveUser(UserEntity user){
        encodePassword(user);
        userRepo.save(user);
    }

    public RoleEntity getOrCreateRole(String roleName){
        Optional<RoleEntity> role = roleRepo.findByRoleName(roleName);
        if (role.isPresent()){
            return role.get();
        }
        else {
            RoleEntity newRole = new RoleEntity(roleName);
            return roleRepo.save(newRole);
        }
    }

    private void encodePassword(UserEntity user){
        String encodedPassword = passwordEncoder.encode(user.getPasswordHash());
        user.setPasswordHash(encodedPassword);
    }

}
