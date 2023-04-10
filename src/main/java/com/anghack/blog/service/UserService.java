package com.anghack.blog.service;

import com.anghack.blog.payload.UserDto;
import java.util.List;

/**
 *
 * @author anaghack
 */
public interface UserService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
