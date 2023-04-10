package com.anghack.blog.service.Impl;

import com.anghack.blog.entity.User;
import com.anghack.blog.exception.ResourceNotFoundException;
import com.anghack.blog.payload.UserDto;
import com.anghack.blog.repository.UserRepo;
import com.anghack.blog.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author anghack
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    
    private final  UserRepo userRepo;

    
    
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
       User user = this.userRepo.findById(userId).orElseThrow(() ->  new ResourceNotFoundException("User", " id", userId));
       
       user.setName(userDto.getName());
       user.setEmail(userDto.getEmail());
       user.setPassword(userDto.getPassword());
       user.setAbout(userDto.getAbout());
       
       User updatedUser = this.userRepo.save(user);
       
       UserDto userDtoResponse = this.userToDto(updatedUser);
       
       return userDtoResponse;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", " Id", userId));
        
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        
        List<User> users = this.userRepo.findAll();
        
        List<UserDto> userDtos =  users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
        
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id", userId));
        
        this.userRepo.delete(user);
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