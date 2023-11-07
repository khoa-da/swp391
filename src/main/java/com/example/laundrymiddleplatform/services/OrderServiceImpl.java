package com.example.laundrymiddleplatform.services;

import com.example.laundrymiddleplatform.entities.Order;
import com.example.laundrymiddleplatform.repositories.OrderRepository;
import com.example.laundrymiddleplatform.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        int totalPrice = order.getPrice() * order.getQuantity();
        order.setTotal_price(totalPrice);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> getOrderId(Long Id) {
        Optional<Order> orderId = orderRepository.findById(Id);
        return orderId;

    }
}
