package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SetUpResponse {
    private Object data;
    private  boolean isSuccessful;
}
