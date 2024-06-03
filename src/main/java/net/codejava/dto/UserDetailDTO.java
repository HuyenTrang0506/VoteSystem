package net.codejava.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.codejava.entity.Voter;

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
    private Set<Voter> voters;
    //i want to have set of election id only=)))))))))))))))))))
    // Constructors, getters, setters


}
