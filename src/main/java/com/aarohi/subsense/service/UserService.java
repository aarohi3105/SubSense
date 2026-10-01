package com.aarohi.subsense.service;

import com.aarohi.subsense.dto.UserResponseDTO;
import com.aarohi.subsense.entity.User;
import com.aarohi.subsense.exception.InvalidCredentialsException;
import com.aarohi.subsense.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserService(UserRepository userRepository ,
                       PasswordEncoder passwordEncoder, JwtService jwtService){ // receives the repository object
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponseDTO registerUser(User user) {

        user.setRole("USER");

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }
    public String loginUser(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password")
                );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return jwtService.generateToken(user.getEmail());
    }
}


//**The Repository object will be passed to the constructor and stored in the `userRepository` variable.**
//
//And later:
//
//**The User object will come into the `user` parameter of the `registerUser()` method, and it will be passed to the database through `userRepository.save(user)`.**