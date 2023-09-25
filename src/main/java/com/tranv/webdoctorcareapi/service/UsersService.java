package com.tranv.webdoctorcareapi.service;

import com.tranv.webdoctorcareapi.dto.CreateDoctorDTO;
import com.tranv.webdoctorcareapi.dto.CreatePatientDTO;
import com.tranv.webdoctorcareapi.dto.DoctorDTO;
import com.tranv.webdoctorcareapi.dto.UserDTO;
import com.tranv.webdoctorcareapi.entity.Status;
import com.tranv.webdoctorcareapi.entity.Users;


import java.util.List;

public interface UsersService {
    void saveUser(Users users);

    boolean isEmailAlreadyExists(String email);

    Users findUserByEmail(String email);

    UserDTO findUserById(int userId);

    List<DoctorDTO> findBySpecializationsName(String specialtyName);

    void createDoctor(CreateDoctorDTO doctorDTO);

    void createPatient(CreatePatientDTO patientDTO);

    void unlockPatient(int patientId);

    void lockPatient(int patientId, Status status);

    void unlockDoctor(int doctorId);

    void lockDoctor(int doctorId, Status status);
}
