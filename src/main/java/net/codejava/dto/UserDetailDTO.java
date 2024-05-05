package net.codejava.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {
    private Long id;
    private String fullname;
    private String email;
    private byte[] avatarUrl;
    private Set<String> roles; // Assuming roles are represented as strings
    private Set<Long> electionIds; // Assuming election ids are represented as longs
    // Constructors, getters, setters


}
