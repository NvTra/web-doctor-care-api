package com.tranv.webdoctorcareapi.controller;

import com.tranv.webdoctorcareapi.dto.createAppointmentDTO;
import com.tranv.webdoctorcareapi.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    // add mapping for POST / book appointment
    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody createAppointmentDTO appointment) {
        try {
            //create a new apointment
            appointmentService.bookAppointment(appointment);
            return ResponseEntity.ok("Bạn đã đặt lịch khám thành công.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Có lỗi xảy ra vui lòng thử lại");
        }
    }


}
