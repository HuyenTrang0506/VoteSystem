package net.codejava.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String fullname;
    private String email;
    private String accessToken;
    private byte[] avatarUrl;
    private Set<String> roles;
    private boolean isPro;// Assuming roles are represented as strings


}
