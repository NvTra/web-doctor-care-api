package com.tranv.webdoctorcareapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String gender;
    private String avatar;
    private String description;
}
