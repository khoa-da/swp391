package com.example.laundrymiddleplatform.services;

import com.example.laundrymiddleplatform.entities.OrderDetail;
import com.example.laundrymiddleplatform.repositories.OrderDetailRepository;
import com.example.laundrymiddleplatform.services.interfaces.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component

public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    @Override
    public void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public Optional<OrderDetail> getOrderDetailId(Long Id) {
        Optional<OrderDetail> orderDetailId = orderDetailRepository.findById(Id);
        return orderDetailId;
    }
}
