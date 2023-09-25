package com.tranv.webdoctorcareapi.service;

import com.tranv.webdoctorcareapi.entity.Clinics;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ClinicsService {
    List<Clinics> getHighlightClinics(Pageable pageable);

    List<Clinics> generalSearch(String keyword);
}
