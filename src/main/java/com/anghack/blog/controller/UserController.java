package com.anghack.blog.controller;

import com.anghack.blog.payload.UserDto;
import com.anghack.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author anghack
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    
    
    
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        
        UserDto createUserDto = this.userService.createUser(userDto);
                
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

}
