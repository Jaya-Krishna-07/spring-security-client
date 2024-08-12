package com.client.service;


import com.client.entity.User;
import com.client.model.UserModel;
import lombok.Data;

public interface UserRegistrationService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);
}
