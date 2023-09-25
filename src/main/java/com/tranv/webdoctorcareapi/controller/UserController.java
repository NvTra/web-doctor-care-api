package com.tranv.webdoctorcareapi.controller;

import com.tranv.webdoctorcareapi.dto.UserDTO;
import com.tranv.webdoctorcareapi.entity.Appointment;
import com.tranv.webdoctorcareapi.service.AppointmentService;
import com.tranv.webdoctorcareapi.service.MailService;
import com.tranv.webdoctorcareapi.service.PatientsService;
import com.tranv.webdoctorcareapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private MailService mailService;
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        UserDTO theUser = usersService.findUserById(userId);
        if (theUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy : " + userId);
        }
        return ResponseEntity.ok(theUser);
    }

    // Send medical information via email
    @GetMapping("/sendMedicalRecord")
    public String SendMedicalRecordByEmail(@RequestParam int appointmentId) {
        try {
            Appointment appointment = appointmentService.findAppointmentById(appointmentId);
            String subject = "Thông tin bệnh lý";
            String body = "Tên bệnh nhân " + appointment.getPatients().getUsers().getName();
            // create PDF File
            byte[] pdfByte = appointmentService.generatePdfFile(appointment);
            //create File Name
            String fileName = appointment.getPatients().getUsers().getName() + "_thông tin khám chữa bệnh.PDF";
            // send email to patient
            mailService.sendPdfEmail(fileName, appointment.getPatients().getUsers().getEmail(), subject, body, pdfByte);
            return "Đã gửi thông tin bệnh lý thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "có lỗi xảy ra khi gửi mail ";
        }
    }
}
