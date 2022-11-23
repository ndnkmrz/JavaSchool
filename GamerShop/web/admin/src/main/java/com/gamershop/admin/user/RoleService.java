package com.gamershop.admin.user;

import com.gamershop.shared.entity.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private final RoleRepository roleRepo;
    public RoleService(RoleRepository roleRepo){
        this.roleRepo = roleRepo;
    }

    public RoleEntity getOrCreateRole(String roleName){
        return roleRepo.findByRoleName(roleName).orElseGet(()-> roleRepo.save(new RoleEntity(roleName)));
    }


    public List<String> listRoles(){
        var roleList = (List<RoleEntity>) roleRepo.findAll();
        return roleList.stream().map(role-> toString()).toList();
    }
}
