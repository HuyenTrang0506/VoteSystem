package net.codejava.controller;

import net.codejava.dto.AuthRequest;
import net.codejava.dto.AuthResponse;
import net.codejava.dto.UserDTO;
import net.codejava.entity.Role;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/auth")

public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtUtil;
    private final AuthenticationManager authManager;
    @Autowired
    public AuthController(UserService userService, JwtTokenUtil jwtUtil, AuthenticationManager authManager) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid  AuthRequest authRequest) {

        try {
            User createdUser = userService.registerUser(authRequest);

            UserDTO response = new UserDTO(createdUser.getId(), createdUser.getFullname(), createdUser.getEmail(), null, createdUser.getAvatarUrl(), createdUser.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
            return ResponseEntity.ok(response);
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

            UserDTO response =new UserDTO(user.getId(), user.getFullname(), user.getEmail(),accessToken, user.getAvatarUrl(), user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //check auth in security context
           Authentication authcheck= SecurityContextHolder.getContext().getAuthentication();

            if (authcheck != null && authcheck.isAuthenticated()) {
                Collection<? extends GrantedAuthority> authorities = authcheck.getAuthorities();

                // Check for specific roles
                for (GrantedAuthority authority : authorities) {
                    if (authority.getAuthority().equals("ROLE_ADMIN")) {
                        // User has the ROLE_ADMIN role
                        // Perform actions specific to users with this role
                        System.out.println("User has the ROLE_ADMIN role");
                    } else if (authority.getAuthority().equals("ROLE_USER")) {
                        // User has the ROLE_USER role
                        // Perform actions specific to users with this role
                        System.out.println("User has the ROLE_USER role");
                    }
                }
            }
            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            CustomErrorResponse response = new CustomErrorResponse(LocalDateTime.now(), HttpStatus.UNAUTHORIZED.value(), "Login error, please try again!" , "/auth/login");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<User> forgotPassword(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.forgotPassword(email));
    }

}
