package com.example.laundrymiddleplatform.api;

import com.example.laundrymiddleplatform.entities.Order;
import com.example.laundrymiddleplatform.response.ApiResponse;
import com.example.laundrymiddleplatform.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{Id}")
    public ResponseEntity<ApiResponse> getOrderId(@PathVariable Long Id) throws Exception {
        Optional<Order> order = orderService.getOrderId(Id);
        if(order.isPresent()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Get Order By Id Success!")
                    .data(order)
                    .build());
        }else{
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Can't find any order with id = " + Id)
                    .build());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Order Created Successfully")
                .data(savedOrder)
                .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("List of Orders")
                .data(orders)
                .build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        int totalPrice = order.getPrice() * order.getQuantity();
        order.setTotal_price(totalPrice);
        order.setOrderId(id); // Ensure the ID is set on the order to update
        Order updatedOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Order Updated Successfully")
                .data(updatedOrder)
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Order Deleted Successfully")
                .build());
    }
}
