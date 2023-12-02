package com.imanali.SpringQuickStart.api.controller;

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

import java.util.List;

@Controller
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserControllerV1 {

    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserDto userDto) {
        return  new ResponseEntity<>(userService.addNewUser(userDto), HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) throws UserNotFoundException {
        User user = userService.getUser(id);
        return user;
    }
}
