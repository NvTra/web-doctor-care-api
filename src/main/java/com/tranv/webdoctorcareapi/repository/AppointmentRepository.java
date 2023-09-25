package com.tranv.webdoctorcareapi.repository;

import com.tranv.webdoctorcareapi.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    Appointment findAppointmentById(int appointmentId);

    @Query(value = "SELECT * FROM appointments a WHERE a.patients_id = :patientId", nativeQuery = true)
    List<Appointment> findByPatientId(@Param("patientId") int patientId);

    @Query(value = "SELECT * from appointments a WHERE a.doctor_id= :doctorId",nativeQuery = true)
    List<Appointment> findByDoctorId(@Param("doctorId") int doctorId);


}
