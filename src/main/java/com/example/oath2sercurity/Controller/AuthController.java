package com.example.oath2sercurity.Controller;

import com.example.oath2sercurity.Config.Security.TokenProvider;
import com.example.oath2sercurity.DTO.LoginDTO;
import com.example.oath2sercurity.DTO.SignupDTO;
import com.example.oath2sercurity.Repository.UserRepository;
import com.example.oath2sercurity.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final TokenProvider tokenProvider;

    private final UserService userService;


    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          TokenProvider tokenProvider,
                          UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupDTO signupDTO) {
        if (userRepository.existsByEmail(signupDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email already in use");
        }
        if (userService.registerNewUser(signupDTO)) {
            return ResponseEntity.ok().body("Register new user successfully!");
        } else {
            return ResponseEntity.badRequest().body("Register new user failure!");
        }
    }
}
