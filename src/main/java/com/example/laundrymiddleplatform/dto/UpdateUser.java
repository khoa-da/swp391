package com.example.laundrymiddleplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUser {
    private String email;

    private String password;

    private String role;

    private String lastName;

    private String firstName;
}
