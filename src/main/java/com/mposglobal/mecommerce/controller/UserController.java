package com.mposglobal.mecommerce.controller;

import com.mposglobal.mecommerce.config.TokenProvider;
import com.mposglobal.mecommerce.dto.UserDto;
import com.mposglobal.mecommerce.model.AuthToken;
import com.mposglobal.mecommerce.model.LoginUser;
import com.mposglobal.mecommerce.model.User;
import com.mposglobal.mecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('Admin')")
    @GetMapping(value="/")
    public List<User> getAllUser() {
        return userService.findAll();
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping(value="/register")
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/{username}")
    public User retrieveUser(@PathVariable String username) {

        Optional<User> userToRetrieve = userService.findOne(username);

        if (userToRetrieve.isEmpty()) {
            throw new UsernameNotFoundException("User with the specified username not found");
        }

        return userToRetrieve.get();
    }
}
