package com.anghack.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author anghack
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    
    private String message;
    private boolean  success;

}
