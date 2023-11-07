package com.example.laundrymiddleplatform.services.interfaces;

import com.example.laundrymiddleplatform.entities.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    Order saveOrder(Order order);

    List<Order> getAllOrders();

    void deleteOrder(Long id);

    Optional<Order> getOrderId(Long Id);
}
