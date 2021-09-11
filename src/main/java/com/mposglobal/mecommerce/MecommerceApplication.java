package com.mposglobal.mecommerce;

import com.mposglobal.mecommerce.model.Role;
import com.mposglobal.mecommerce.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MecommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MecommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(RoleRepository roleRepository) throws Exception {
        return (String[] args) -> {
            Role adminRole = new Role("Admin");
            Role userRole = new Role("User");
            roleRepository.save(adminRole);
            roleRepository.save(userRole);
        };
    }
}
