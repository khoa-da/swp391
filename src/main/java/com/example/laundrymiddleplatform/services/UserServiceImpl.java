package com.example.laundrymiddleplatform.services;

import com.example.laundrymiddleplatform.dto.UpdateUser;
import com.example.laundrymiddleplatform.dto.UserLoginResponse;
import com.example.laundrymiddleplatform.entities.User;
import com.example.laundrymiddleplatform.repositories.UserRepository;
import com.example.laundrymiddleplatform.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(String username, String email, String password, String lastname, String firstname) {
        User saveUser = userRepository.save(User.builder()
             .username(username)
             .email(email)
             .password(password)
             .lastName(lastname)
             .firstName(firstname)
             .role("User")
             .build());
        return saveUser;
    }
    public UserLoginResponse login(String username, String password){
        User loginUser = userRepository.findByUsernameAndPassword(username,password);
        if(loginUser == null){
            return null;
        }else{
            UserLoginResponse userLoginResponse = new UserLoginResponse(loginUser.getUserId(), loginUser.getUsername(), loginUser.getRole());
            return userLoginResponse;
        }
    }

    @Override
    public Optional<User> getUserId(Long Id) {
        Optional<User> userId = userRepository.findById(Id);
        return userId;
    }

    @Override
    public List<User> userList() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public User updateUser(UpdateUser updateUser, Long id) {

        User saveUser = getUserId(id).get();
        saveUser.setEmail(updateUser.getEmail());
        saveUser.setPassword(updateUser.getPassword());
        saveUser.setRole(updateUser.getRole());
        saveUser.setFirstName(updateUser.getFirstName());
        saveUser.setLastName(updateUser.getLastName());
        saveUser.setUpdatedAt(new Date());

        return userRepository.save(saveUser);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
