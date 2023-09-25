package com.tranv.webdoctorcareapi.controller;

import com.tranv.webdoctorcareapi.dto.PatientDTO;
import com.tranv.webdoctorcareapi.entity.Appointment;
import com.tranv.webdoctorcareapi.service.AppointmentService;
import com.tranv.webdoctorcareapi.service.PatientsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/list")
@AllArgsConstructor
public class ListController {
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private AppointmentService appointmentService;

    // Get mapping for retrieving patients by doctor
    @GetMapping("/patients")
    public ResponseEntity<?> getPatientsByDoctor(@RequestParam("doctorId") int doctorId) {
        try {
            // Retrieve a list of patients by doctorId from patientsService
            List<PatientDTO> patientsList = patientsService.getPatientsByDoctor(doctorId);
            if (patientsList.isEmpty()) {
            // Return an OK response
                return ResponseEntity.ok("Danh sách bệnh nhân trống");
            } else {
            // Return an OK response
                return ResponseEntity.ok(patientsList);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi trong quá trình xử lý số liệu");
        }
    }

    // Get mapping for retrieving appointments by patient
    @GetMapping("patients/{patientId}")
    public ResponseEntity<?> getAppointmentByPatient(@PathVariable int patientId) {
        // Retrieve a list of appointment by patientId
        List<Appointment> appointments = appointmentService.findAppointmentByPatientId(patientId);
        if (appointments == null) {
        // Return a Not Found response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy lịch khám chữa bệnh của bệnh nhân: " + patientId);
        }
        // Return an OK response
        return ResponseEntity.ok(appointments);
    }
    @GetMapping("appointmentByDoctor/{doctorId}")
    public ResponseEntity<?> getAppointmentByDoctor(@PathVariable int doctorId) {
        // Retrieve a list of appointment by doctorId
        List<Appointment> appointments = appointmentService.findAppointmentByDoctorId(doctorId);
        if (appointments == null) {
        // Return a Not Found response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm danh sách lịch khám bệnh của Bác sỹ: " + doctorId);
        }
        // Return an OK response
        return ResponseEntity.ok(appointments);
    }
}
