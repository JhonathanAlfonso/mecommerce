package com.mposglobal.mecommerce.service.impl;

import com.mposglobal.mecommerce.dto.UserDto;
import com.mposglobal.mecommerce.model.Role;
import com.mposglobal.mecommerce.model.User;
import com.mposglobal.mecommerce.repository.UserRepository;
import com.mposglobal.mecommerce.service.RoleService;
import com.mposglobal.mecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        User user = userOptional.get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        Role nUserRole = user.getRole();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + nUserRole.getName()));
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Optional<User> findOne(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserDto userDto) {

        User nUser = userDto.getUserFromDto();
        nUser.setPassword(bcryptEncoder.encode(userDto.getPassword()));

        String roleName = userDto.getIsAdmin() ? "Admin" : "User";
        Role nUserRole = roleService.findByName(roleName);
        nUser.setRole(nUserRole);

        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with given username already exits");
        } else if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with given email already exits");
        }

        return userRepository.save(nUser);
    }


}
