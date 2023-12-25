package com.imanali.SpringQuickStart.repository;

import com.imanali.SpringQuickStart.model.Role;
import com.imanali.SpringQuickStart.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.List;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser() {

        String plainPassword = "123456";
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(plainPassword);
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            User admin = User.builder()
                    .firstName("Iman")
                    .lastName("Ali")
                    .email("iman@gmail.com")
                    .password(encodedPassword)
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);

            User manager = User.builder()
                    .firstName("Ishak")
                    .lastName("Ahmed")
                    .email("ishak@gmail.com")
                    .password(encodedPassword)
                    .role(Role.MANAGER)
                    .build();
            userRepository.save(manager);

            User customer = User.builder()
                    .firstName("Ismail")
                    .lastName("Ahmed")
                    .email("ismail@gmail.com")
                    .password(encodedPassword)
                    .role(Role.CUSTOMER)
                    .build();
            userRepository.save(customer);
        }
    }

    @Test
    public void printAllUsers() {
        List<User> userList = userRepository.findAll();
        System.out.println("userList= "+ userList);
    }

}