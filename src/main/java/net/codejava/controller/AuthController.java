package net.codejava.controller;

import net.codejava.dto.AuthRequest;
import net.codejava.dto.AuthResponse;
import net.codejava.entity.User;
import net.codejava.exception_handler.CustomErrorResponse;
import net.codejava.jwt.JwtTokenUtil;
import net.codejava.service.service_interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.time.LocalDateTime;


@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid  AuthRequest authRequest) {

        try {
            User createdUser = userService.registerUser(authRequest);
            AuthResponse authResponse = new AuthResponse(createdUser.getId(), createdUser.getEmail(), null, createdUser.getRoles(), createdUser.getFullname());
            return ResponseEntity.ok(authResponse);
        } catch (DataIntegrityViolationException ex) {
            // Xử lý ngoại lệ nếu email đã tồn tại trong cơ sở dữ liệu
            CustomErrorResponse response = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Email is already taken ", "/auth/register");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(response);
        } catch (Exception ex) {
            // Xử lý các ngoại lệ khác mà không phải là do email trùng lặp
            CustomErrorResponse response = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Error when create new user: " + ex.getMessage(), "/auth/register");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        }
    }

    // Get current user is active
    @GetMapping()
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        String email = principal.getName();
        User user = userService.findUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getId(),user.getEmail(), accessToken, user.getRoles(), user.getFullname());

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<User> forgotPassword(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.forgotPassword(email));
    }

}
