package com.example.laundrymiddleplatform.api;

import com.example.laundrymiddleplatform.entities.OrderDetail;
import com.example.laundrymiddleplatform.response.ApiResponse;
import com.example.laundrymiddleplatform.services.interfaces.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order_detail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/{Id}")
    public ResponseEntity<ApiResponse> getOrderDetailId(@PathVariable Long Id) throws Exception {
        Optional<OrderDetail> orderDetail = orderDetailService.getOrderDetailId(Id);
        if(orderDetail.isPresent()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Get OrderDetail Id")
                    .data(orderDetail)
                    .build());
        }else{
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Can find order detail with id = " + Id)
                    .build());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        OrderDetail savedOrderDetail = orderDetailService.saveOrderDetail(orderDetail);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("OrderDetail Created Successfully")
                .data(savedOrderDetail)
                .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetails();
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("List of OrderDetails")
                .data(orderDetails)
                .build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateOrderDetail(@PathVariable Long id, @RequestBody OrderDetail orderDetail) {
        orderDetail.setOrderDetailId(id); // Ensure the ID is set on the orderDetail to update
        OrderDetail updatedOrderDetail = orderDetailService.saveOrderDetail(orderDetail);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("OrderDetail Updated Successfully")
                .data(updatedOrderDetail)
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteOrderDetail(@PathVariable Long id) {
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("OrderDetail Deleted Successfully")
                .build());
    }
}
