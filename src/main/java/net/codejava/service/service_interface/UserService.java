package net.codejava.service.service_interface;

import net.codejava.dto.AuthRequest;
import net.codejava.dto.UserDetailDTO;
import net.codejava.entity.User;


import java.security.Principal;
import java.util.List;

public interface UserService {
    User save(User user);

    User changePassword(String oldPassword, String password, Principal principal);
    User forgotPassword(String email);

    User changeAvatar(String url, Principal principal);

    List<UserDetailDTO> getAllUser();


    Boolean delete(User user);


    Boolean sendEmail(String to, String subject, String body) ;
    User findUserByEmail(String email);


    User registerUser(AuthRequest authRequest);
}
