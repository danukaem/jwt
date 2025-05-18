package com.assignment.eventManagementSystem.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDTO {
    private String message;
    private Object data;
    private HttpStatus statusCode;


}
