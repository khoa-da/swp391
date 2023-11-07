package com.example.laundrymiddleplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateUser {
    private String username;

    private String email;

    private String password;

    private String lastName;

    private String firstName;

}
