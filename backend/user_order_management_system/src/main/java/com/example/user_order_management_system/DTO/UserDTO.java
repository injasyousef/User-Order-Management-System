package com.example.user_order_management_system.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String role;

    private String pass;

}