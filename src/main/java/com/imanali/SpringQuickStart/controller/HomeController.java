package com.imanali.SpringQuickStart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

//    private final UserService userService;
    @GetMapping("/")
    public String home() {

        return "index";
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
//        String result = userService.validateRegistrationVerification(token);
//        if (result.equalsIgnoreCase("valid")) {
//            return "User verifies successfully";
//        }
        return "Bad user";
    }
}
