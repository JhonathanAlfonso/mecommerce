package com.mposglobal.mecommerce.service;

import com.mposglobal.mecommerce.model.Role;

public interface RoleService {
    Role findByName(String name);
}
