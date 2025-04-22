package com.rootcodeinterview.online_library_system.DTO;

import java.util.Map;

public class ExceptionResponseDto {
    private String message;
    private Map<String, Object> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
