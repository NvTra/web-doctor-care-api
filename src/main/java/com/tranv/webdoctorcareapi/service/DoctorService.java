package com.tranv.webdoctorcareapi.service;

import com.tranv.webdoctorcareapi.entity.Doctor;
import com.tranv.webdoctorcareapi.entity.Users;

public interface DoctorService {
    Doctor findDoctorById(int theId);

    void saveDoctor(Doctor doctor);

    Doctor getDoctorByUserId(int userId);

    Users findUserByDoctorId(int doctorId);
}
