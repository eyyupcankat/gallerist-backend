package com.eyyupcankat.gallerist.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ControllerResponse<T> {

    private Integer status;

    private T payload;

    private String errorMessage;

    public static  <T> ControllerResponse<T> ok(T payload) {
        ControllerResponse<T> controllerResponse = new ControllerResponse<T>();

        controllerResponse.setStatus(HttpStatus.OK.value());
        controllerResponse.setPayload(payload);
        controllerResponse.setErrorMessage(null);

        return controllerResponse;
    }

    public static  <T> ControllerResponse<T> error(String errorMessage) {
        ControllerResponse<T> controllerResponse = new ControllerResponse<T>();

        controllerResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        controllerResponse.setErrorMessage(errorMessage);
        controllerResponse.setPayload(null);

        return controllerResponse;
    }
}
