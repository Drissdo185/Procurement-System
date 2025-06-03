package com.bank.digital_procurement.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private Integer statusCode = 1000; //success
    private String message;
    private T data;
    private String timestamp;

    public ApiResponse(Integer statusCode) {
        this.statusCode = statusCode;
        this.timestamp = getCurrentDateTime();
    }

    public ApiResponse(Integer statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
        this.timestamp = getCurrentDateTime();
    }

    public ApiResponse(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = getCurrentDateTime();
    }

    public ApiResponse(Integer statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.timestamp = getCurrentDateTime();
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(1000);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(1000, data);
    }

    public static <T> ApiResponse<T> error(String message, Integer statusCode) {
        return new ApiResponse<>(statusCode, message);
    }

    public static <T> ApiResponse<T> error(String message, Integer statusCode, T data) {
        return new ApiResponse<>(statusCode, message, data);
    }

    private String getCurrentDateTime() {
        return java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
