package net.codejava.dto;


import lombok.*;
import net.codejava.entity.Role;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter

public class AuthResponse {
    private Long id;
    private String email;
    private String accessToken;
    private Set<Role> roles;
    private String username;

//constructor
    public AuthResponse(Long id, String email, String accessToken, Set<Role> roleSet, String username) {
        this.id = id;
        this.email = email;
        this.accessToken = accessToken;
        roles = new HashSet<>();
        roles=roleSet;
        this.username = username;
    }
 //no argument constructor
    public AuthResponse() {
        roles = new HashSet<>();
    }

}
