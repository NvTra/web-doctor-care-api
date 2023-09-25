package com.tranv.webdoctorcareapi.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DoctorDTO {
    private Integer id;
    private String name;
    private String address;
    private String phone;
    private String description;
    private String specializationsName;

}
