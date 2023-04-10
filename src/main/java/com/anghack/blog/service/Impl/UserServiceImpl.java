package com.anghack.blog.service.Impl;

import com.anghack.blog.entity.User;
import com.anghack.blog.payload.UserDto;
import com.anghack.blog.repository.UserRepo;
import com.anghack.blog.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author anghack
 */
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepo userRepo;

    
    
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto user, Integer userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UserDto getUserById(Integer userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<UserDto> getAllUsers() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteUser(Integer userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public User dtoToUser(UserDto userDto){
        User user = new User();
        
        user.setId( userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        
        userDto.setId( user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        
        return userDto;
    }
}
