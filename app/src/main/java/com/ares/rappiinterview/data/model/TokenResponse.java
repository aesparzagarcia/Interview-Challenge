package com.ares.rappiinterview.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ares on 2019-05-27
 */
@SuppressWarnings("unused")
public class TokenResponse extends BaseResponse {

    @SerializedName("status_code")
    private String status_code;
    @SerializedName("status_message")
    private String status_message;
    @SerializedName("success")
    private String success;
    @SerializedName("request_token")
    private String request_token;

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }
}

