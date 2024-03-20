package net.codejava;

import net.codejava.entity.Role;
import net.codejava.repository.RoleRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    public void run(String... args) throws Exception {
        roleRepository.save(new Role("ROLE_ADMIN"));
       roleRepository.save(new Role("ROLE_USER"));

    }


}
