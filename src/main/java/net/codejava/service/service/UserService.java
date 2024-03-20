package net.codejava.service.service;

import net.codejava.entity.User;


import java.security.Principal;
import java.util.List;

public interface UserService {
    User save(User user);

    User changePassword(String oldPassword, String password, Principal principal);
    User forgotPassword(String email);

    User changeAvatar(String url, Principal principal);


    Boolean delete(User user);

    List<User> getAllUser();
    Boolean sendEmail(String to, String subject, String body) ;
    User findUserByEmail(String email);


}
