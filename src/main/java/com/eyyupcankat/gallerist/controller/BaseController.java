package com.eyyupcankat.gallerist.controller;

public class BaseController {

    public <T> ControllerResponse<T> ok(T payload){
        return ControllerResponse.ok(payload);
    }

    public <T> ControllerResponse<T> error(String message){
        return ControllerResponse.error(message);
    }

}
