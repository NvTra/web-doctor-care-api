package com.tranv.webdoctorcareapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PatientDTO {
    private int id;
    private String name;
    private String gender;
    private String address;
    private String phone;
    private List<String> basicPathology;
    private List<String> descriptionPathology;
}
