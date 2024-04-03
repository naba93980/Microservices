package com.hystrix.demo.hystrixdemo.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorMessage {
    private HttpStatus status;
    private String message;
}
