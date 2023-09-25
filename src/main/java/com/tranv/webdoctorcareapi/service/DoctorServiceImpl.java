package com.tranv.webdoctorcareapi.service;

import com.tranv.webdoctorcareapi.entity.Doctor;
import com.tranv.webdoctorcareapi.entity.Users;
import com.tranv.webdoctorcareapi.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Find a doctor by ID
    @Override
    public Doctor findDoctorById(int theId) {
        return doctorRepository.findDoctorById(theId);
    }

    // Save a doctor
    @Override
    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    // Get a doctor by user ID
    @Override
    public Doctor getDoctorByUserId(int userId) {
        return doctorRepository.getDoctorByUsers(userId);
    }

    // Find a user by doctor ID
    @Override
    public Users findUserByDoctorId(int doctorId) {
        return doctorRepository.findUserByDoctorId(doctorId);
    }


}
