package com.rootcodeinterview.online_library_system.Util;

import com.rootcodeinterview.online_library_system.DTO.ExceptionResponseDto;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {
    public static ExceptionResponseDto getValidationErrorResponse(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        Map<String, Object> data = new HashMap<>();

        errors.forEach(error -> data.put(error.getField(), error.getRejectedValue()));

        ExceptionResponseDto exceptionResponseDto=new ExceptionResponseDto();
        exceptionResponseDto.setMessage("Request payload contain invalid data");
        exceptionResponseDto.setData(data);
        return exceptionResponseDto;
    }
}
