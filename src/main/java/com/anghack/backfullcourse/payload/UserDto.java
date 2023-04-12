package com.anghack.backfullcourse.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class UserDto {
    private int id;

    @NotEmpty
    @Size(min = 4, message = "Username must be min of 4 characters")
    private String name;

    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "Password not be blank")
    private String password;
    private String about;
}
