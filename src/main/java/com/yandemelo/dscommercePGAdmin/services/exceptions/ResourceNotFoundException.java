package com.yandemelo.dscommercePGAdmin.services.exceptions;

public class ResourceNotFoundException extends RuntimeException { //RuntimeException não exige ter Try Catch

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
    
}
