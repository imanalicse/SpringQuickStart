package com.imanali.SpringQuickStart.api.controller;

import com.imanali.SpringQuickStart.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HomeController {

    private final UserService userService;
    @GetMapping("/")
    public String home() {
        return "Home page Ismail";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateRegistrationVerification(token);
        if (result.equalsIgnoreCase("valid")) {
            return "User verifies successfully";
        }
        return "Bad user";
    }
}
