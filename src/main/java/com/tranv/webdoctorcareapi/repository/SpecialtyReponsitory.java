package com.tranv.webdoctorcareapi.repository;

import com.tranv.webdoctorcareapi.entity.Specializations;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialtyReponsitory extends JpaRepository<Specializations, Long> {
    @Query("select s from Specializations s order by s.numberChoose DESC")
    List<Specializations> getListOfFeaturedSpecialties(Pageable pageable);

    Specializations findByName(String name);
}
