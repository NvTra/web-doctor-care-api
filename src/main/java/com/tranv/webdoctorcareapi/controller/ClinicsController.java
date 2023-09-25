package com.tranv.webdoctorcareapi.controller;

import com.tranv.webdoctorcareapi.entity.Clinics;
import com.tranv.webdoctorcareapi.service.ClinicsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clinics")
public class ClinicsController {
    @Autowired
    private ClinicsServiceImpl clinicsService;

    // Get mapping for retrieving highlighted clinics
    @GetMapping("/highlightClinics")
    public ResponseEntity<?> getHighlightClinics() {
        try {
            // Retrieve a list of highlighted clinics from clinicsService
            List<Clinics> clinicsList = clinicsService.getHighlightClinics(Pageable.ofSize(3));
            if (clinicsList.isEmpty()) {
            // Return an OK response
                return ResponseEntity.ok("Không có công ty nổi bật nào");
            }
            // Return an OK response
            return ResponseEntity.ok(clinicsList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi xử lý số liệu");
        }
    }
}
