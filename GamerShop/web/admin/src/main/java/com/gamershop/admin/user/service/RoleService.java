package com.gamershop.admin.user.service;

import com.gamershop.admin.user.interfaces.IRoleService;
import com.gamershop.admin.user.repo.RoleRepository;
import com.gamershop.shared.entity.RoleEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleService implements IRoleService {
    private final RoleRepository roleRepo;
    public RoleService(RoleRepository roleRepo){
        this.roleRepo = roleRepo;
    }

    @Transactional
    public RoleEntity getOrCreateRole(String roleName){
        return roleRepo.findByRoleName(roleName).orElseGet(()-> roleRepo.save(new RoleEntity(roleName)));
    }

    public List<String> listRoles(){
        var roleList = (List<RoleEntity>) roleRepo.findAll();
        return roleList.stream().map(RoleEntity::getRoleName).toList();
    }
}
