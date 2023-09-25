package com.tranv.webdoctorcareapi.service;

import com.itextpdf.text.DocumentException;
import com.tranv.webdoctorcareapi.dto.createAppointmentDTO;
import com.tranv.webdoctorcareapi.entity.Appointment;
import com.tranv.webdoctorcareapi.entity.Status;

import java.util.List;

public interface AppointmentService {
    Appointment findAppointmentById(int appointmentId);

    void bookAppointment(createAppointmentDTO appointment);

    List<Appointment> findAppointmentByPatientId(int patientId);

    List<Appointment> findAppointmentByDoctorId(int doctorId);

    void acceptAppointment(int appointmentId);

    void cancelAppointment(int appointmentId, Status status);

    byte[] generatePdfFile(Appointment appointment) throws DocumentException;
}
