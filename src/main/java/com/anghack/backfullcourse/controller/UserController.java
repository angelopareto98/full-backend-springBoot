package com.anghack.backfullcourse.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anghack.backfullcourse.payload.ApiResponse;
import com.anghack.backfullcourse.payload.UserDto;
import com.anghack.backfullcourse.service.UserService;

import jakarta.validation.Valid;

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
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        UserDto createUserDto = this.userService.createUser(userDto);

        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") int idUser) {

        UserDto updatedUser = this.userService.updateUser(userDto, idUser);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") int idUser) {
        this.userService.deleteUser(idUser);

        return new ResponseEntity<>(new ApiResponse("User deleted successfuly", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
        List<UserDto> users = this.userService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") int idUser) {
        UserDto userSelected = this.userService.getUserById(idUser);

        return ResponseEntity.ok(userSelected);
    }

}
