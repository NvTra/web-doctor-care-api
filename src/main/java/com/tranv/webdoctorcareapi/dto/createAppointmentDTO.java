package com.tranv.webdoctorcareapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class createAppointmentDTO {
    private int doctorId;
    private int patientId;
    private String appointmentDate;
    private String appointmentTime;
    private double consultationFee;
    private String reason;
    private String description;
}
