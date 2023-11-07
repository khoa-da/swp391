package com.example.laundrymiddleplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateStore {
    private String storeName;

    private String location;

    private Long ownerId;

    private String ownerName;
}
