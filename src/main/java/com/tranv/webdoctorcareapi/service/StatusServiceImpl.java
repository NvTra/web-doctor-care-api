package com.tranv.webdoctorcareapi.service;

import com.tranv.webdoctorcareapi.entity.Status;
import com.tranv.webdoctorcareapi.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    // Save a status
    @Override
    public void saveStatus(Status status) {
        statusRepository.save(status);
    }

    // Delete a status by ID
    @Override
    public void deleteStatus(int theId) {
        statusRepository.deleteById(theId);
    }

    // Find a status by appointment ID
    @Override
    public Status findByAppointmentId(int appointmentId) {
        return statusRepository.findByAppointmentId(appointmentId);
    }


}
