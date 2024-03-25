package net.codejava;

import net.codejava.entity.Role;
import net.codejava.repository.RoleRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.service_interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
public class VotingSystem {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(VotingSystem.class, args);
    }
@Bean
    public void init() {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
        roleRepository.save(admin);
        roleRepository.save(user);
    }


}
