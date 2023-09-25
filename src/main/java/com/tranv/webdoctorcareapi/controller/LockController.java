package com.tranv.webdoctorcareapi.controller;

import com.tranv.webdoctorcareapi.entity.Status;
import com.tranv.webdoctorcareapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lock")
public class LockController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UsersService usersService;

    //  Accept / cancel patient's examination schedule
    @PutMapping("/accept/{appointmentId}")
    public ResponseEntity<String> acceptAppointment(@PathVariable("appointmentId") int appointmentId) {
        try {
            //accept appointment
            appointmentService.acceptAppointment(appointmentId);
            return ResponseEntity.ok("Lịch hẹn đã được chấp nhận thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi trong quá trình xử lý");
        }
    }

    @PostMapping("/cancel/{appointmentId}")
    public ResponseEntity<?> cancelAppointment(@PathVariable("appointmentId") int appointmentId,
                                               @RequestBody Status status) {
        try {
            //cancel appointment
            appointmentService.cancelAppointment(appointmentId, status);
            return ResponseEntity.ok("Lịch hẹn đã hủy thành công vì lý do: " +status.getName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi trong quá trình xử lý");
        }
    }

    //    Lock/unlock the patient's account
    @PutMapping("/unlockPatient/{patientId}")
    public ResponseEntity<String> unlockPatient(@PathVariable("patientId") int patientId) {
        try {
            usersService.unlockPatient(patientId);
            return ResponseEntity.ok("Đã mở khóa tài khoản bệnh nhân thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi trong quá trình xử lý");
        }
    }

    @PostMapping("/lockPatient/{patientId}")
    public ResponseEntity<?> lockPatient(@PathVariable("patientId") int patientId,
                                         @RequestBody Status status) {
        try {
            usersService.lockPatient(patientId, status);
            return ResponseEntity.ok("Đã khóa tài khoản bệnh nhân thành công vì: "+status.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi trong quá trình xử lý");
        }
    }

    //    Lock/unlock the doctor's account
    @PutMapping("/unlockDoctor/{doctorId}")
    public ResponseEntity<String> unlockDoctor(@PathVariable("doctorId") int userId) {
        try {
            usersService.unlockDoctor(userId);
            return ResponseEntity.ok("Đã mở khóa tài khoản bác sỹ thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi trong quá trình xử lý");
        }
    }

    @PostMapping("/lockDoctor/{doctorId}")
    public ResponseEntity<?> lockDoctor(@PathVariable("doctorId") int doctorId,
                                        @RequestBody Status status) {
        try {
            usersService.lockDoctor(doctorId, status);
            return ResponseEntity.ok("Đã khóa tài khoản bác sĩ thành công vì: " +status.getName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi trong quá trình xử lý");
        }
    }
}
