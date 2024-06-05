package net.codejava.controller;

import net.codejava.dto.UserDTO;
import net.codejava.dto.modifyUser.ChangeAvatarDTO;
import net.codejava.dto.modifyUser.ChangePasswordDTO;
import net.codejava.entity.User;
import net.codejava.service.service_interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<Object> getAllUser() {

        List<UserDTO> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }
//    @RolesAllowed("USER")
    @PatchMapping("/password")
    public ResponseEntity<User> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO, Principal principal) {
        User user = userService.changePassword(changePasswordDTO.getOldPassword(), changePasswordDTO.getNewPassword(), principal);
        return ResponseEntity.ok(user);
    }

//    @RolesAllowed("USER")
    @PatchMapping("/avatar")
    public ResponseEntity<User> changeAvatar(@RequestBody ChangeAvatarDTO changeAvatarDTO, Principal principal) {
        return ResponseEntity.ok(userService.changeAvatar(changeAvatarDTO.getUrl(), principal));
    }



}

