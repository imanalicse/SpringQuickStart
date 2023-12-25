package com.imanali.SpringQuickStart.service;

import com.imanali.SpringQuickStart.model.Order;
import com.imanali.SpringQuickStart.model.User;
import com.imanali.SpringQuickStart.repository.OrderRepository;
import com.imanali.SpringQuickStart.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public Order createOrder(Order order) throws Exception {
        try {
            String loginEmail = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByEmail(loginEmail).orElseThrow(()-> new AuthenticationException("Login user not found") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            });
            order.setFirstname(user.getFirstName());
            order.setUser(user);
            return orderRepository.save(order);
        }
        catch (Exception exception) {
            System.out.println("create order error:" + exception.getMessage());
            throw new Exception("create order error:" + exception.getMessage());
        }
    }
}
