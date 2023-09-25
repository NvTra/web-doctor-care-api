package com.tranv.webdoctorcareapi;

import com.tranv.webdoctorcareapi.dto.PatientDTO;
import com.tranv.webdoctorcareapi.entity.Appointment;
import com.tranv.webdoctorcareapi.entity.Status;
import com.tranv.webdoctorcareapi.service.AppointmentService;
import com.tranv.webdoctorcareapi.service.PatientsService;
import com.tranv.webdoctorcareapi.service.StatusService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WebDoctorcareApiApplicationTests {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private PatientsService patientsService;

    @Test
    void contextLoads() {
        List<PatientDTO> patientsList = patientsService.getPatientsByDoctor(1);
        for (PatientDTO p : patientsList) {
            System.out.println(p.getId());
            System.out.println(p.getName());
        }
    }

}
