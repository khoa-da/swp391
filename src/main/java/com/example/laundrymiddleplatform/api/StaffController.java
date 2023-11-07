package com.example.laundrymiddleplatform.api;

import com.example.laundrymiddleplatform.entities.Staff;
import com.example.laundrymiddleplatform.response.ApiResponse;
import com.example.laundrymiddleplatform.services.interfaces.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")

public class StaffController {
    @Autowired
    private StaffService staffService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createStaff(@RequestBody Staff staff) {
        Staff savedStaff = staffService.saveStaff(staff);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Staff Created Successfully")
                .data(savedStaff)
                .build());
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllStaff() {
        List<Staff> staffMembers = staffService.getAllStaff();
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("List of Staff Members")
                .data(staffMembers)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getStaffById(@PathVariable Long id) {
        Staff staff = staffService.getStaffById(id);
        if(staff != null){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Get Staff By Id Success")
                    .data(staff)
                    .build());
        }else{
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Get Staff By Id Fail")
                    .build());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateStaff(@PathVariable Long id, @RequestBody Staff staff) {
        staff.setStaffId(id);
        Staff updatedStaff = staffService.saveStaff(staff);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Staff Updated Successfully")
                .data(updatedStaff)
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .success(true)
                .message("Staff Deleted Successfully")
                .build());
    }
}
