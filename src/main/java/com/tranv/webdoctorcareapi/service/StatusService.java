package com.tranv.webdoctorcareapi.service;

import com.tranv.webdoctorcareapi.entity.Status;

public interface StatusService {
    void saveStatus(Status status);

    void deleteStatus(int theId);

    Status findByAppointmentId(int appointmentId);
}
