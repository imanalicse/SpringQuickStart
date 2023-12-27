package com.imanali.SpringQuickStart.event.listner;

import com.imanali.SpringQuickStart.event.RegistrationCompleteEvent;
import com.imanali.SpringQuickStart.model.User;
import com.imanali.SpringQuickStart.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@Slf4j
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    @Autowired
    private UserService userService;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveRegistrationVerificationTokenForUser(token, user);
        // Send mail to user
        String url = event.getApplicationUrl() + "/verifyRegistration?token="
                + token;
        log.info("click the link to verify your account: {}", url);
    }
}
