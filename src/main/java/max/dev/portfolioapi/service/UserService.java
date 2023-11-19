package max.dev.portfolioapi.service;


import max.dev.portfolioapi.dto.AuthenticationRequest;
import max.dev.portfolioapi.dto.UserDTO;
import max.dev.portfolioapi.dto.UserRequest;
import max.dev.portfolioapi.exceptions.GlobalException;
import max.dev.portfolioapi.mapper.UserMapper;
import max.dev.portfolioapi.model.UserModel;
import max.dev.portfolioapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;


//  ---------------------------------------- HANDLE CURRENT USER ----------------------------------------------

    public String getCurrentUser () {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            String username = userDetails.getUsername();
            return username;
        }
        return "user";
    }

    public boolean userEqualsCurrent (String userEmail) {

        return getCurrentUser().equals(userEmail);

    }

//  ------------------------------------------- GET ---------------------------------------------

    public UserDTO getUser(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new GlobalException("User with id " + id + " not found"));
        if (!userEqualsCurrent(user.getEmail())){
            throw new GlobalException("Not allow to view this");
        }
        return userMapper.toUserDTO(user);
    }

    public List<UserDTO> getUsers() {
        var users = userRepository.findAll();
        List<UserDTO> response = users.stream()
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
        return response;
    }

//  ------------------------------------------- UPDATE ---------------------------------------------

    public UserModel updateUser(Long userId, UserRequest request) {
        var user = userRepository.findById(userId).orElseThrow(() -> new GlobalException("User not found"));
        if(!userEqualsCurrent(user.getEmail())) {
            throw new GlobalException("Not allowed to do this: Invalid user");
        }
//        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setGithubProfileUrl(request.getGithubProfileUrl());
        user.setLinkedinProfileUrl(request.getLinkedinProfileUrl());
        user.setTwitterProfileUrl(request.getTwitterProfileUrl());
//        user.setPassword(request.getPassword());

        userRepository.save(user);
    return user;
    }

    public String changePassword(Long id,AuthenticationRequest request) {
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new GlobalException("User not found"));
        if (!userEqualsCurrent(user.getEmail())) {
            throw new GlobalException("Your not allowed to do this.");
        }
        if (!user.getId().equals(id)) {
            throw new GlobalException("Your not allowed to do this.");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return "Password Updated";
    }

//  ------------------------------------------- DELETE ---------------------------------------------

    public String deleteUser(Long userId){
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new GlobalException(" User with id " + userId + " not found"));
        if(!userEqualsCurrent(user.getEmail())) {
            throw new GlobalException("Not allowed to do this: Invalid user");
        }
        userRepository.deleteById(userId);
        return "User deleted";
    }



}
