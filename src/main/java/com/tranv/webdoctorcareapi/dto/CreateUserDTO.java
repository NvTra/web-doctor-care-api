package com.tranv.webdoctorcareapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
    private String address;
    private String phone;
    private String gender;
    private String description;
}
