package com.cnkaptan.nebenanandroidchallange.service;

/**
 * Created by cnkaptan on 30/07/16.
 */
public class CustomErrorModel {
    String errorType;
    String errorDesc;

    public CustomErrorModel() {
    }

    public CustomErrorModel(String errorType, String errorDesc) {
        this.errorType = errorType;
        this.errorDesc = errorDesc;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
