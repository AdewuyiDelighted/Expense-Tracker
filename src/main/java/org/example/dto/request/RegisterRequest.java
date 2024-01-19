package org.example.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String password;
    private String email;
}
