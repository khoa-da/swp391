package com.example.laundrymiddleplatform.services.interfaces;

import com.example.laundrymiddleplatform.entities.Staff;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StaffService {
    Staff saveStaff(Staff staff);

    List<Staff> getAllStaff();

    Staff getStaffById(Long id);

    void deleteStaff(Long id);

    Optional<Staff> getStaffId(Long staffId);
}
