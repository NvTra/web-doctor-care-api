package com.tranv.webdoctorcareapi.controller;

import com.tranv.webdoctorcareapi.entity.Specializations;
import com.tranv.webdoctorcareapi.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/specialties")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;

    // Get mapping for retrieving highlighted specialties
    @GetMapping("/highlights")
    public ResponseEntity<?> getHighlightSpecialties() {
        try {
            // Retrieve a list of featured specialties from specialtyService
            List<Specializations> specializations = specialtyService.getListOfFeaturedSpecialties(Pageable.ofSize(3));
            if (specializations.isEmpty()) {
                // Return an OK response
                return ResponseEntity.ok("Không có chuyên khoa nổi bật nào");
            }
            // Return an OK response
            return ResponseEntity.ok(specializations);
        } catch (Exception exc) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi trong quá trình xử lý");
        }
    }
}
