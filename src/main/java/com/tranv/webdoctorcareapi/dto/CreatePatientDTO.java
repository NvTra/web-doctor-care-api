package com.tranv.webdoctorcareapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePatientDTO extends CreateUserDTO{
    private String dateOfBirth;
}
