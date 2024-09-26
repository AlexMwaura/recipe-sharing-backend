package com.recipe.sharing.services;

import com.recipe.sharing.dto.LoginRequest;
import com.recipe.sharing.dto.LoginResponse;
import com.recipe.sharing.entities.User;
import com.recipe.sharing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUsername(loginRequest.getUsername()));

        LoginResponse response = new LoginResponse();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(loginRequest.getPassword())) {
                response.setMessage("Login successful");
                response.setSuccess(true);
            } else {
                response.setMessage("Invalid password");
                response.setSuccess(false);
            }
        } else {
            response.setMessage("User not found");
            response.setSuccess(false);
        }

        return response;
    }
}
