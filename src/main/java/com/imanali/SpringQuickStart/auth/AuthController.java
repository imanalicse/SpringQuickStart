package com.imanali.SpringQuickStart.auth;

import com.imanali.SpringQuickStart.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/admin/login")
    public String adminLoginForm(Model model) {
        model.addAttribute("user", new AuthenticationRequest());
        return "auth/admin_login";
    }

    @GetMapping("/customer/login")
    public String customerLoginForm(Model model) {
        model.addAttribute("user", new AuthenticationRequest());
        return "auth/customer_login";
    }
}
