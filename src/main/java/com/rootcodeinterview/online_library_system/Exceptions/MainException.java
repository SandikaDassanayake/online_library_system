package com.rootcodeinterview.online_library_system.Exceptions;

import com.rootcodeinterview.online_library_system.DTO.ExceptionResponseDto;

import java.util.Map;

public class MainException extends RuntimeException{

    private final String message;
    private final Map<String, Object> data;

    public MainException(String message, Map<String, Object> data) {
        super(message);
        this.message = message;
        this.data = data;
    }

    public ExceptionResponseDto getExceptionResponseDto() {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.setMessage(message);
        exceptionResponseDto.setData(data);
        return exceptionResponseDto;
    }
}
