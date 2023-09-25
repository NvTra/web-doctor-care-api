package com.tranv.webdoctorcareapi.repository;

import com.tranv.webdoctorcareapi.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findByAppointmentId(int appointmentId);

    Status findByPatientsId(int patientId);
}
