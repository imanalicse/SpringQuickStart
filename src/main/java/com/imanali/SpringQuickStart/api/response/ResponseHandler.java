package com.imanali.SpringQuickStart.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseHandler {
    private static DataResponseModel generateSuccessResponse(Map<String, Object> data, HttpStatus httpStatus) {
        DataResponseModel dataResponseModel = new DataResponseModel();
        dataResponseModel.setStatus(true);
        dataResponseModel.setStatusCode(httpStatus.value());
        dataResponseModel.setData(data);
        return dataResponseModel;
    }

    public static ResponseEntity<DataResponseModel> oKResponse(Map<String, Object> data) {
        HttpStatus httpStatus = HttpStatus.OK;
        DataResponseModel response = ResponseHandler.generateSuccessResponse(data, httpStatus);
        return new ResponseEntity<DataResponseModel>(response, httpStatus);
    }

    public static ResponseEntity<DataResponseModel> createdResponse(Map<String, Object> data) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        DataResponseModel response = ResponseHandler.generateSuccessResponse(data, httpStatus);
        return new ResponseEntity<DataResponseModel>(response, httpStatus);
    }
}
