package com.anghack.backfullcourse.service;

import java.util.List;

import com.anghack.backfullcourse.payload.UserDto;

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

    Object login(UserDto userDto);
}
