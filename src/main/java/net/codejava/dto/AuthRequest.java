package net.codejava.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter

public class AuthRequest {


    @NotNull
    @Email
    @Length(min = 5, max = 50)
    private String email;
    private String fullname;
    @NotNull
    @Length(min = 5, max = 50)
    private String password;
   
    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
