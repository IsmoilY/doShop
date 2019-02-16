package com.apple.selfone.model;

public class ResponseData<T> {
    private boolean success;
    private String reason;
    private T data;

    public ResponseData() {
        this.success = true;
    }

    public ResponseData(boolean success, String reason, T data) {
        this.success = success;
        this.reason = reason;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
