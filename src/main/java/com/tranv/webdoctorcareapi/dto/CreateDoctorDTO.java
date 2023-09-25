package com.tranv.webdoctorcareapi.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateDoctorDTO extends CreateUserDTO{
    private String training;
    private String achievements;
    private String specializationsName;
}
