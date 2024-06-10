package net.codejava.service.service_interface;

import net.codejava.dto.AuthRequest;
import net.codejava.dto.UserDTO;
import net.codejava.entity.User;


import java.security.Principal;
import java.util.List;

public interface UserService {
    User save(User user);

    User changePassword(String oldPassword, String password, Principal principal);
    User forgotPassword(String email);

    User changeAvatar(String url, Principal principal);

    List<UserDTO> getAllUser();
    UserDTO findUserById(Long id);
    UserDTO changePro(Long id);
    Boolean delete(User user);
    Boolean delete(Long id);

    Boolean sendEmail(String to, String subject, String body) ;
    User findUserByEmail(String email);


    User registerUser(AuthRequest authRequest);
}
