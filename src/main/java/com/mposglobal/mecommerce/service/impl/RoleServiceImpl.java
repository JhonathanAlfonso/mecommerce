package com.mposglobal.mecommerce.service.impl;

import com.mposglobal.mecommerce.model.Role;
import com.mposglobal.mecommerce.repository.RoleRepository;
import com.mposglobal.mecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
