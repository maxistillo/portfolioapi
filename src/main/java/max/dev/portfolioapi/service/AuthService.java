package max.dev.portfolioapi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import max.dev.portfolioapi.dto.AuthenticationRequest;
import max.dev.portfolioapi.dto.AuthenticationResponse;
import max.dev.portfolioapi.exceptions.GlobalException;
import max.dev.portfolioapi.model.UserModel;
import max.dev.portfolioapi.model.Role;
import max.dev.portfolioapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenService tokenService;
    @Autowired
    JwtService jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;


//    --------------------------------------- REGISTER ----------------------------------------
    @Transactional
    public AuthenticationResponse signUp(AuthenticationRequest request){
        var user = UserModel.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new GlobalException("Email already in user");
        }
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        tokenService.saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(savedUser.getEmail())
                .role(savedUser.getRole().toString())
                .userId(savedUser.getId().toString())
                .build();
    }

//  ----------------------------- AUTHENTICATE -----------------------------

    public AuthenticationResponse authenticate (AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new GlobalException("User with email " + request.getEmail() + " not found."));
        var jwtToken = jwtService.generateToken(user);
        tokenService.revokeAllUserTokens(user);
        tokenService.saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole().toString())
                .userId(user.getId().toString())
                .build();
    }

}
