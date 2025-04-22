package com.rootcodeinterview.online_library_system.Exceptions;

import java.util.Map;

public class ResourceNotFoundException extends MainException{
    public ResourceNotFoundException(String message, Map<String, Object> data) {
        super(message, data);
    }
}
