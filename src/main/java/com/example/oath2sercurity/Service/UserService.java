package com.example.oath2sercurity.Service;

import com.example.oath2sercurity.DTO.SignupDTO;
import com.example.oath2sercurity.Model.User;
import com.example.oath2sercurity.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerNewUser(SignupDTO signupDTO) {
        User user = new User();
        user.setUserName(signupDTO.getUsername());
        user.setName(signupDTO.getName());
        user.setEmail(signupDTO.getEmail());
        user.setBirthday(signupDTO.getBirthday());
        user.setPassWord(passwordEncoder.encode(signupDTO.getPassword()));

        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
