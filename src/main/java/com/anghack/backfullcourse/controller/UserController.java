package com.anghack.backfullcourse.controller;

import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
import com.anghack.backfullcourse.payload.ApiResult;
import com.anghack.backfullcourse.payload.AuthenticationRequest;
import com.anghack.backfullcourse.payload.UserDto;
import com.anghack.backfullcourse.service.UserService;
import com.anghack.backfullcourse.util.QrcodeGenerator;
import com.google.zxing.WriterException;

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

    @PostMapping("/auth/register")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) throws WriterException, IOException {

        UserDto createUserDto = this.userService.createUser(userDto);

        QrcodeGenerator.generateQRCode(createUserDto);

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
    public ResponseEntity<ApiResult> getAllUser() {
        List<UserDto> users = this.userService.getAllUsers();

        ApiResult apiResult = new ApiResult();
        apiResult.setCode(200);
        apiResult.setSuccess(true);
        apiResult.setData(users);

        return new ResponseEntity<>(apiResult, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResult> getSingleUser(@PathVariable("userId") int idUser) {
        UserDto userSelected = this.userService.getUserById(idUser);

        ApiResult apiResult = new ApiResult();
        apiResult.setCode(200);
        apiResult.setSuccess(true);
        apiResult.setData(userSelected);

        return ResponseEntity.ok(apiResult);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest authenticationRequest) {
        Object jwt = this.userService.login(authenticationRequest);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

}
