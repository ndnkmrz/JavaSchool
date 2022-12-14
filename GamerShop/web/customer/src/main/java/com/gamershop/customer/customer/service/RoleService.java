package com.gamershop.customer.customer.service;

import com.gamershop.customer.customer.interfaces.IRoleService;
import com.gamershop.customer.customer.repo.RoleRepository;
import com.gamershop.shared.entity.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {
    private final RoleRepository roleRepo;
    public RoleService(RoleRepository roleRepo){
        this.roleRepo = roleRepo;
    }

    public RoleEntity getOrCreateRole(String roleName){
        return roleRepo.findByRoleName(roleName).orElseGet(()-> roleRepo.save(new RoleEntity(roleName)));
    }

    public List<String> listRoles(){
        var roleList = (List<RoleEntity>) roleRepo.findAll();
        return roleList.stream().map(RoleEntity::getRoleName).toList();
    }
}
