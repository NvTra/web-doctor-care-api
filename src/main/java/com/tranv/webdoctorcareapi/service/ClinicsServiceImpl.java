package com.tranv.webdoctorcareapi.service;

import com.tranv.webdoctorcareapi.entity.Clinics;
import com.tranv.webdoctorcareapi.repository.ClinicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicsServiceImpl implements ClinicsService {
    @Autowired
    private ClinicsRepository clinicsRepository;

    public ClinicsServiceImpl(ClinicsRepository clinicsRepository) {
        this.clinicsRepository = clinicsRepository;
    }
    // Get highlighted clinics
    @Override
    public List<Clinics> getHighlightClinics(Pageable pageable) {
        return clinicsRepository.getHighlightClinics(pageable);
    }
    // Perform a general search for clinics
    @Override
    public List<Clinics> generalSearch(String keyword) {
        return clinicsRepository.generalSearch(keyword);
    }
}
