package com.tranv.webdoctorcareapi.repository;

import com.tranv.webdoctorcareapi.entity.Clinics;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicsRepository extends JpaRepository<Clinics, Long> {
    @Query("select c from Clinics c order by c.numberAppointments DESC")
    List<Clinics> getHighlightClinics(Pageable pageable);

    @Query(value = "select c.* from clinics c \n" +
             "where concat(c.name,' ',c.address,' ',c.consultation_fee) like %:keyword% "
            , nativeQuery = true)
    List<Clinics> generalSearch(@Param("keyword") String keyword);
}
