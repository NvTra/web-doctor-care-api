package com.tranv.webdoctorcareapi.service;

import com.tranv.webdoctorcareapi.entity.Specializations;
import com.tranv.webdoctorcareapi.repository.SpecialtyReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialyServiceImpl implements SpecialtyService {
    @Autowired
    private SpecialtyReponsitory specialtyReponsitory;

    public SpecialyServiceImpl(SpecialtyReponsitory specialtyReponsitory) {
        this.specialtyReponsitory = specialtyReponsitory;
    }

    // Get a list of featured specialties
    @Override
    public List<Specializations> getListOfFeaturedSpecialties(Pageable pageable) {
        return specialtyReponsitory.getListOfFeaturedSpecialties(pageable);
    }
    // Get a specialization by name
    @Override
    public Specializations getSpecializationByName(String name) {
        return specialtyReponsitory.findByName(name);
    }
}
