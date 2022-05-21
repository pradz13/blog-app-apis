package com.app.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty(message = "Name cannot be blank")
    private String name;

    @NotEmpty(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotEmpty(message = "Password cannot be blank")
    private String password;

    @NotEmpty(message = "About cannot be blank")
    private String about;

}
