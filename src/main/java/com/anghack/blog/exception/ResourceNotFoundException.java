package com.anghack.blog.exception;

import lombok.Data;

/**
 *
 * @author anghack
 */
@Data
public class ResourceNotFoundException extends RuntimeException{
    
    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : %l", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    
    

}
