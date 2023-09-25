package com.tranv.webdoctorcareapi.repository;

import com.tranv.webdoctorcareapi.entity.Patients;
import com.tranv.webdoctorcareapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Integer> {
    Patients findPatientById(int patientId);

    @Query(value = "select distinct p.* from patients p \n" +
            "JOIN appointments a on a.patients_id =p.id\n" +
            "JOIN doctor d on a.doctor_id =d.id\n" +
            "WHERE d.id=?1",
            nativeQuery = true)
    List<Patients> getPatientsByDoctor(int doctorId);

    @Query("select p from Patients p join p.users u where u.id =?1")
    Patients getPatientsByUsers(int userId);

    @Query("select u from Users u join u.patients p where p.id=?1")
    Users findUserByPatientId(int patientId);
}
