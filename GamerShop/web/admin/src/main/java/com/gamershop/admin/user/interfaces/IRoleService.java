package com.gamershop.admin.user.interfaces;

import com.gamershop.shared.entity.RoleEntity;

import java.util.List;

public interface IRoleService {
    RoleEntity getOrCreateRole(String roleName);
    List<String> listRoles();
}
