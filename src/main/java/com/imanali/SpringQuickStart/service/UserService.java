package com.imanali.SpringQuickStart.service;

import com.imanali.SpringQuickStart.exception.UserNotFoundException;
import com.imanali.SpringQuickStart.model.RegistrationVerificationToken;
import com.imanali.SpringQuickStart.model.User;
import com.imanali.SpringQuickStart.repository.RegistrationVerificationTokenRepository;
import com.imanali.SpringQuickStart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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

    public String validateRegistrationVerification(String token) {
        RegistrationVerificationToken registrationVerificationToken = registrationVerificationTokenRepository.findByToken(token);
        if (registrationVerificationToken == null) {
            return "invalid";
        }
        User user = registrationVerificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if (registrationVerificationToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0) {
            registrationVerificationTokenRepository.delete(registrationVerificationToken);
            return "expired";
        }
        user.setEnabled(true);
        userRepository.save(user);
        return "valid";
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(Long id) throws UserNotFoundException {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}
