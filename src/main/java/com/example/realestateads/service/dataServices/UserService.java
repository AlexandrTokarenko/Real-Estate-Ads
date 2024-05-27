package com.example.realestateads.service.dataServices;

import com.example.realestateads.dto.UserRestoreDTO;
import com.example.realestateads.entity.User;
import com.example.realestateads.exception.UserAlreadyExistException;
import com.example.realestateads.repository.TokenRepository;
import com.example.realestateads.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    public User findById(String email) {

        return userRepository.findById(email).orElse(null);
    }

    public void save(User user) throws UserAlreadyExistException {

        if (emailExists(user.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

      //  var jwtToken = jwtService.generateToken(user);
     //   var refreshToken = jwtService.generateRefreshToken(user);
      //  saveUserToken(savedUser, jwtToken);
    }

    private boolean emailExists(String email) {

        return userRepository.findById(email).isPresent();
    }

    public void updateActivationCode(String userEmail, String activationCode, Timestamp timestamp) {

        userRepository.updateActivationCode(activationCode,timestamp,userEmail);
    }

    public void updatePassword(UserRestoreDTO user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.updatePassword(user.getPassword(),user.getEmail());
    }

    public void updateVerifyEmail(User user, Boolean isVerify) {

        userRepository.updateVerifyEmail(isVerify,user);
    }
}
