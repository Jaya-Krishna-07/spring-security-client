package com.client.controller;

import com.client.entity.User;
import com.client.event.RegistrationCompleteEvent;
import com.client.model.UserModel;
import com.client.service.UserRegistrationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
public class RegistrationController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PostMapping("/register")
    public ResponseEntity<UserModel> registerUser(@RequestBody UserModel userModel, HttpServletRequest request){
        User user = userRegistrationService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return new ResponseEntity<>(userModel, HttpStatus.CREATED);
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userRegistrationService.validateVerificationToken(token);
        return result.equalsIgnoreCase("valid") ? "User Verified successfully" : "bad user";
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://localhost" +
                request.getContextPath() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }
}
