package com.tranv.webdoctorcareapi.controller;

import com.tranv.webdoctorcareapi.dto.DoctorDTO;
import com.tranv.webdoctorcareapi.entity.Clinics;
import com.tranv.webdoctorcareapi.service.ClinicsService;
import com.tranv.webdoctorcareapi.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private ClinicsService clinicsService;

    // Search by specialty
    @GetMapping
    public ResponseEntity<?> seachSpecializations(@RequestParam("search") String condition) {
        try {
            //Call method to search for doctors by specialty
            List<DoctorDTO> usersList = usersService.findBySpecializationsName(condition);
            if (usersList.isEmpty()) {
                // If no doctor is found, returns the error code NOT_FOUND and the message that the specialty was not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy chuyên khoa: " + condition);
            } else {
                // If found, returns a list of doctors
                return ResponseEntity.ok(usersList);
            }
        } catch (Exception exp) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi trong quá trình xử lý số liệu");
        }
    }

    @GetMapping("/generalSearch")
    public ResponseEntity<?> generalSearch(@RequestParam("keyword") String keyword) {
        try {
            // Call method to search for related information by keyword
            List<Clinics> clinicsList = clinicsService.generalSearch(keyword);
            if (clinicsList.isEmpty()) {
                // If no information is found, return not found message
                return ResponseEntity.ok("Không tìm thấy thông tin liên quan đến " + keyword);
            } else {
                // If found, returns a list of information
                return ResponseEntity.ok(clinicsList);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi trong quá trình xử lý số liệu");
        }
    }
}
