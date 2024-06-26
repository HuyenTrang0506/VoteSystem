package net.codejava.service;

import net.codejava.dto.AuthRequest;
import net.codejava.dto.UserDTO;
import net.codejava.entity.Role;
import net.codejava.entity.User;


import net.codejava.repository.RoleRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.service_interface.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userrepo;

    private final PasswordEncoder passwordEncoder;

    private final JavaMailSender javaMailSender;

    private final RoleRepository roleRepository;
    @Autowired
    public UserServiceImpl(UserRepository userrepo, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender, RoleRepository roleRepository) {
        this.userrepo = userrepo;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.roleRepository = roleRepository;
    }
    @Override
    public User registerUser(AuthRequest authRequest) {
        User user = new User();
        //email
        user.setEmail(authRequest.getEmail());
        //fullname
        user.setFullname(authRequest.getFullname());
        //add role for user
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        //password
        String rawPassword = authRequest.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        return userrepo.save(user);
    }

    @Override
    public UserDTO changePro(Long id) {
        Optional<User> user = userrepo.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setPro(!existingUser.getIsPro());
           userrepo.save(existingUser);
            return new UserDTO(existingUser.getId(), existingUser.getFullname(), existingUser.getEmail(), null, existingUser.getAvatarUrl(), existingUser.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),existingUser.getIsPro());
        } else {
            throw new NoSuchElementException("User not found with email: " + id);
        }
    }
    @CacheEvict(value = "users", key = "#id")
    @Override
    public User save(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        return userrepo.save(user);
    }

    public List<UserDTO> getAllUser() {
        List<User> users = userrepo.findAll();
        return users.stream()
                .map(user -> new UserDTO(user.getId(), user.getFullname(), user.getEmail(),null, user.getAvatarUrl(), user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),user.getIsPro()))
                .collect(Collectors.toList());
    }
    @Cacheable(value = "users", key = "#id")
    @Override
    public UserDTO findUserById(Long id) {
        Optional<User> user = userrepo.findById(id);
        if (user.isPresent()) {
            User u = user.get();
            return new UserDTO(u.getId(), u.getFullname(), u.getEmail(), null, u.getAvatarUrl(), u.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),u.getIsPro());
        } else {
            throw new NoSuchElementException("User not found with id: " + id);
        }
    }
    @Cacheable(value = "users", key = "#email")
    @Override
    public User findUserByEmail(String email) {
        Optional<User> u = userrepo.findByEmail(email);
        User user = null;
        if (u.isPresent()) {
            user = u.get();
        }
        return user;
    }


    @CacheEvict(value = "users", allEntries = true)
    @Override
    public Boolean delete(User user) {
        try {
            userrepo.delete(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public Boolean delete(Long id) {
        return userrepo.findById(id)
                .map(user -> {
                    userrepo.delete(user);
                    return true;
                }).orElse(false);
    }



    @Override
    public Boolean sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tranghuyen9696@gmail.com");

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        System.out.println("Send mail successfully");
        return true;
    }

    public String generateOTP() {
        // Tạo một chuỗi ngẫu nhiên
        String randomString = "your-random-string"; // Thay thế bằng chuỗi ngẫu nhiên thực tế

        // Áp dụng thuật toán SHA-256 để mã hóa chuỗi ngẫu nhiên
        String otp = DigestUtils.sha256Hex(randomString);

        // Trích xuất 6 ký tự đầu tiên của mã OTP
        otp = otp.substring(0, 6);

        return otp;
    }
    @Override
    public User changePassword(String oldPassword, String password, Principal principal) {
        String email = principal.getName();
        Optional<User> user = userrepo.findByEmail(email);
        String encodedPassword = passwordEncoder.encode(password);

        if (user.isPresent()) {
            User existingUser = user.get();
            if (!passwordEncoder.matches(oldPassword, existingUser.getPassword())) {
                throw new NoSuchElementException("Wrong password");
            }
            existingUser.setPassword(encodedPassword);
            return userrepo.save(existingUser);
        } else {
            throw new NoSuchElementException("User not found with email: " + email);
        }
    }

    @Override
    public User forgotPassword(String email) {
        String otp = generateOTP();

        Optional<User> user = userrepo.findByEmail(email);
        String encodedPassword = passwordEncoder.encode(otp);

        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setPassword(encodedPassword);
            sendEmail(email, "Your new password is: ", otp);
            System.out.println("Your new password is " + otp);
            return userrepo.save(existingUser);
        } else {
            throw new NoSuchElementException("User not found with email: " + email);
        }
    }

    @Override
    public User changeAvatar(String url, Principal principal) {
        String email = principal.getName();
        Optional<User> user = userrepo.findByEmail(email);
        if (user.isPresent()) {
            User existingUser = user.get();
            byte[] avatarBytes = url.getBytes(StandardCharsets.UTF_8); // Convert String to byte[]
            existingUser.setAvatarUrl(avatarBytes);
            return userrepo.save(existingUser);
        } else {
            throw new NoSuchElementException("User not found with email: " + email);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return  userrepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

    }
}
