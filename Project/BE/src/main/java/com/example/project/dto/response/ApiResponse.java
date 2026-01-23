package com.example.project.dto.response;


import java.util.HashMap;
import java.util.Map;

public class ApiResponse <T> {
    private String status;
    private T payload;
    private Map<String, String> error;
    private Map<String, Object> metadata;

    public void ok() {
        this.status = "SUCCESS";
    }

    public void ok(T data) {
        this.status = "SUCCESS";
        this.payload = data;
    }

    public void ok(HashMap<String, Object> metadata) {
        this.status = "SUCCESS";
        this.metadata = metadata;
    }

    public void ok(T data, HashMap<String, Object> metadata) {
        this.status = "SUCCESS";
        this.payload = data;
        this.metadata = metadata;
    }

    public void error(Map<String, String> error) {
        this.status = "ERROR";
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}
