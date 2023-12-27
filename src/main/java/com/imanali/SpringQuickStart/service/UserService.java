package com.imanali.SpringQuickStart.service;

import com.imanali.SpringQuickStart.exception.UserNotFoundException;
import com.imanali.SpringQuickStart.model.RegistrationVerificationToken;
import com.imanali.SpringQuickStart.model.User;
import com.imanali.SpringQuickStart.repository.RegistrationVerificationTokenRepository;
import com.imanali.SpringQuickStart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RegistrationVerificationTokenRepository registrationVerificationTokenRepository;

    public User addUser(User user) {
        // User user = User.build(0L, userDto.getFirstname(), userDto.getLastname(), userDto.getEmail());
       return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return user;
    }

    public void saveRegistrationVerificationTokenForUser(String token, User user) {
        RegistrationVerificationToken registrationVerificationToken = new RegistrationVerificationToken(user, token);
        registrationVerificationTokenRepository.save(registrationVerificationToken);
    }
}
