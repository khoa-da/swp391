package com.example.laundrymiddleplatform.services.interfaces;

import com.example.laundrymiddleplatform.entities.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderDetailService {
    OrderDetail saveOrderDetail(OrderDetail orderDetail);

    List<OrderDetail> getAllOrderDetails();

    void deleteOrderDetail(Long id);

    Optional<OrderDetail> getOrderDetailId(Long Id);
}
