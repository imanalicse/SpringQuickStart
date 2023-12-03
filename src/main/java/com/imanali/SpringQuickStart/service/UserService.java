package com.imanali.SpringQuickStart.service;

import com.imanali.SpringQuickStart.dto.UserDto;
import com.imanali.SpringQuickStart.exception.UserNotFoundException;
import com.imanali.SpringQuickStart.model.User;
import com.imanali.SpringQuickStart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(UserDto userDto) {
        User user = User.build(0L, userDto.getName(), userDto.getEmail(),
                userDto.getMobile(), userDto.getGender(), userDto.getAge(), userDto.getNationality());
       return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return user;
    }
}
