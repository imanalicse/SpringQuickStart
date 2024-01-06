package com.imanali.SpringQuickStart.admin.user;

import com.imanali.SpringQuickStart.exception.UserNotFoundException;
import com.imanali.SpringQuickStart.model.Role;
import com.imanali.SpringQuickStart.model.User;
import com.imanali.SpringQuickStart.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/users")
    public String showUserList(Model model) {
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "admin/user/index";
    }
    @GetMapping("/users/add")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add new User");
        return "admin/user/form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        userService.save(user);
        String message = "User has been saved successfully";
        if (!user.getId().describeConstable().isEmpty()) {
            message = "User has been edited successfully";
        }
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/admin/users";
    }

    @GetMapping("users/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try
        {
            User user = userService.getUser(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User");
            return "admin/users/form";
        }
        catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "The user not found");
            return "redirect:/admin/users";
        }
    }

    @GetMapping("users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        try
        {
            userService.delete(id);
            redirectAttributes.addFlashAttribute("message", "User deleted successfully");
            return "redirect:/admin/users";
        }
        catch (UserNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "The user not found");
            return "redirect:/admin/users";
        }
    }
}
