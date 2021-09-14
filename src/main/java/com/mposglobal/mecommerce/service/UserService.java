package com.mposglobal.mecommerce.service;

import com.mposglobal.mecommerce.dto.UserDto;
import com.mposglobal.mecommerce.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(UserDto user);

    List<User> findAll();

    Optional<User> findOne(String username);
}
