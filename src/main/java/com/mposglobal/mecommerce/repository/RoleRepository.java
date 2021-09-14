package com.mposglobal.mecommerce.repository;

import com.mposglobal.mecommerce.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findRoleByNameIgnoreCase(String name);
}
