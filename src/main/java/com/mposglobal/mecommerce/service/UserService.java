package com.mposglobal.mecommerce.service;

import com.mposglobal.mecommerce.dto.UserDto;
import com.mposglobal.mecommerce.model.User;

import java.util.List;

public interface UserService {
    User save(UserDto user);
    List<User> findAll();
    User findOne(String username);
}
