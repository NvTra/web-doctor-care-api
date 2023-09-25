package com.tranv.webdoctorcareapi.service;

import com.itextpdf.text.DocumentException;
import com.tranv.webdoctorcareapi.dto.PatientDTO;
import com.tranv.webdoctorcareapi.entity.Patients;
import com.tranv.webdoctorcareapi.entity.Users;

import java.io.IOException;
import java.util.List;

public interface PatientsService {
    Patients findPatientsById(int theId);

    List<PatientDTO> getPatientsByDoctor(int doctorId);

    Patients getPatientsByUser(int userId);

    Users findUserByPatientId(int patientId);

    void save(Patients patients);
}
