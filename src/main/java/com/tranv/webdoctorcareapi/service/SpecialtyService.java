package com.tranv.webdoctorcareapi.service;

import com.tranv.webdoctorcareapi.entity.Specializations;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpecialtyService {
    List<Specializations> getListOfFeaturedSpecialties(Pageable pageable);
    Specializations getSpecializationByName(String name);
}
