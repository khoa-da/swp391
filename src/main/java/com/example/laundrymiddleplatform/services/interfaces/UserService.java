package com.example.laundrymiddleplatform.services.interfaces;

import com.example.laundrymiddleplatform.dto.UpdateUser;
import com.example.laundrymiddleplatform.dto.UserLoginResponse;
import com.example.laundrymiddleplatform.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    User register(String username, String email, String password, String lastname, String firstname);
    UserLoginResponse login(String username, String password);
    Optional<User> getUserId(Long userId);
    List<User> userList();
    User save(User user);

    User updateUser(UpdateUser updateUser, Long id);

    void deleteById(Long id);
}
