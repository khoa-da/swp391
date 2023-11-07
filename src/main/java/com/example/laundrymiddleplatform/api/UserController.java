package com.example.laundrymiddleplatform.api;
import com.example.laundrymiddleplatform.dto.CreateUser;
import com.example.laundrymiddleplatform.dto.UpdateUser;
import com.example.laundrymiddleplatform.dto.UserLogin;
import com.example.laundrymiddleplatform.dto.UserLoginResponse;
import com.example.laundrymiddleplatform.entities.Product;
import com.example.laundrymiddleplatform.entities.User;
import com.example.laundrymiddleplatform.response.ApiResponse;
import com.example.laundrymiddleplatform.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUser createUser) throws Exception {
        try{
            User user = userService.register(createUser.getUsername(), createUser.getEmail(), createUser.getPassword(), createUser.getLastName(), createUser.getFirstName());
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Create user success!")
                    .data(user)
                    .build());
        }catch (Exception e){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Create user fail! Error: " + e.getMessage())
                    .build());
        }
    }

    @GetMapping("/alluser")
    public ResponseEntity<ApiResponse> getAllUser() throws Exception {
        List<User> userList = userService.userList();
        if(userList.isEmpty()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("List user is empty!")
                    .build());
        }else{
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Get All User")
                    .data(userList)
                    .build());
        }
    }
    @GetMapping("/{Id}")
    public ResponseEntity<ApiResponse> getUserId(@PathVariable Long Id) throws Exception {
        Optional<User> user = userService.getUserId(Id);
        if(user.isPresent()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Get User By ID Success")
                    .data(user)
                    .build());
        }else{
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Get User By ID Fail")
                    .build());
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @RequestBody UpdateUser updateUser) {
        if(!userService.getUserId(id).isPresent()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Update fail! Can't find this user!")
                    .build());
        }else{
            User upUser = userService.updateUser(updateUser, id);
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("User updated successfully")
                    .data(upUser)
                    .build());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        if(!userService.getUserId(id).isPresent()){
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Delete fail! Can't find this user!")
                    .build());
        }else {
            userService.deleteById(id);
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("User deleted successfully")
                    .build());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody UserLogin userLogin) {
        UserLoginResponse response = userService.login(userLogin.getUsername(), userLogin.getPassword());
        if (response == null) {
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(false)
                    .message("Login fail!")
                    .build());
        }else{
            return ResponseEntity.ok(ApiResponse.builder()
                    .success(true)
                    .message("Login success!")
                    .data(response)
                    .build());
        }
    }
}
