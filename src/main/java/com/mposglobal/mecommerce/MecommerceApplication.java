package com.mposglobal.mecommerce;

import com.mposglobal.mecommerce.model.Role;
import com.mposglobal.mecommerce.model.User;
import com.mposglobal.mecommerce.repository.RoleRepository;
import com.mposglobal.mecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class MecommerceApplication {

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public static void main(String[] args) {
        SpringApplication.run(MecommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository) throws Exception {
        return (String[] args) -> {
            Role adminRole = new Role("Admin");
            Role userRole = new Role("User");

            roleRepository.save(adminRole);
            roleRepository.save(userRole);

            User adminUser = new User(
                    "admin",
                    bcryptEncoder.encode("admin"),
                    "admin@email.com",
                    "Jhonathan Salazar",
                    adminRole
            );

            userRepository.save(adminUser);
        };
    }
}
