package com.imanali.SpringQuickStart.api.controller;

import com.imanali.SpringQuickStart.api.response.ResponseHandler;
import com.imanali.SpringQuickStart.dto.UserDto;
import com.imanali.SpringQuickStart.exception.UserNotFoundException;
import com.imanali.SpringQuickStart.model.User;
import com.imanali.SpringQuickStart.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserControllerV1 {

    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto) {
        User user = userService.addUser(userDto);
        Map<String, Object> data = new HashMap<>();
        data.put("user", user);
        return ResponseHandler.createdResponse(data);
    }

    @GetMapping()
    public ResponseEntity<Object> getAllUser() {
        List<User> users = userService.getAllUsers();
        Map<String, Object> data = new HashMap<>();
        data.put("users", users);
        return ResponseHandler.oKResponse(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id) throws UserNotFoundException {
        User user = userService.getUser(id);
        Map<String, Object> data = new HashMap<>();
        data.put("users", user);
        return ResponseHandler.oKResponse(data);
    }
}
