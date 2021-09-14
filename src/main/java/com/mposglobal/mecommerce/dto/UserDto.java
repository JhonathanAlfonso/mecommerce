package com.mposglobal.mecommerce.dto;

import com.mposglobal.mecommerce.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class UserDto {

    @NotBlank
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank
    @Size(min = 3)
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    private Boolean isAdmin = false;

    private Boolean isDisable = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public Boolean getIsDisable() { return isDisable; }

    public User getUserFromDto() {
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setDisabledAt(isDisable ? LocalDateTime.now() : null);

        return user;
    }
}
