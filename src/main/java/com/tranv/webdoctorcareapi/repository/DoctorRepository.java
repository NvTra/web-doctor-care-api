package com.tranv.webdoctorcareapi.repository;

import com.tranv.webdoctorcareapi.entity.Doctor;
import com.tranv.webdoctorcareapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findDoctorById(int theId);

    @Query("select d from Doctor d join d.users u where u.id=?1")
    Doctor getDoctorByUsers(int userId);

    @Query("select u from Users u join u.doctor d where d.id=?1")
    Users findUserByDoctorId(int doctorId);
}
