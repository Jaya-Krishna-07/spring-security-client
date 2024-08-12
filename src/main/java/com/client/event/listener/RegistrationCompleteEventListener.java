package com.client.event.listener;

import com.client.entity.User;
import com.client.event.RegistrationCompleteEvent;
import com.client.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
//        create the verification token for the user registered
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        userRegistrationService.saveVerificationTokenForUser(token, user);

//        send email to verify account
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
        log.info("click the link to verify your account: " + url);
    }
}
