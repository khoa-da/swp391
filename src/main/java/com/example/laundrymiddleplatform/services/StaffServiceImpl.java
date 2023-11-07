package com.example.laundrymiddleplatform.services;

import com.example.laundrymiddleplatform.entities.Staff;
import com.example.laundrymiddleplatform.repositories.StaffRepository;
import com.example.laundrymiddleplatform.services.interfaces.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getStaffById(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public Optional<Staff> getStaffId(Long Id) {
        Optional<Staff> staffId = staffRepository.findById(Id);
        return staffId;
    }
}
