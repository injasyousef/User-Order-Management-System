package com.example.user_order_management_system.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class JWTAuthResponse {


        private String token;

        public JWTAuthResponse(String token) {
            this.token = token;
        }



}
