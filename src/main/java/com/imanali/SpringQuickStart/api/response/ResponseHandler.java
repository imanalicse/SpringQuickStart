package com.imanali.SpringQuickStart.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    private static Object generateSuccessResponse(Object data, HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", true);
        response.put("status_code", httpStatus.value());
        response.put("data", data);
        return response;
    }

    public static ResponseEntity<Object> oKResponse(Object data) {
        HttpStatus httpStatus = HttpStatus.OK;
        Object response = ResponseHandler.generateSuccessResponse(data, httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static ResponseEntity<Object> createdResponse(Object data) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        Object response = ResponseHandler.generateSuccessResponse(data, httpStatus);
        return new ResponseEntity<>(response, httpStatus);
    }
}
