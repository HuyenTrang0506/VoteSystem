package net.codejava.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.codejava.entity.Voter;

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
    private byte[] avatarUrl;
    private Set<String> roles; // Assuming roles are represented as strings




}
