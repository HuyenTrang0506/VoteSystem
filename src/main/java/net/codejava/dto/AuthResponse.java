package net.codejava.dto;


import lombok.*;
import net.codejava.entity.Role;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String email;
    private String accessToken;
    private Set<Role> roles;
    private String username;

//constructor


}
