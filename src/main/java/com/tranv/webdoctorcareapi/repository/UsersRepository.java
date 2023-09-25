package com.tranv.webdoctorcareapi.repository;


import com.tranv.webdoctorcareapi.entity.Doctor;
import com.tranv.webdoctorcareapi.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    Users findByUserName(String username);

    @Query(value = "select d from Doctor d " +
            "inner join d.users u " +
            "inner join d.specializations s " +
            "where s.name like %:keyword% order by d.id")
    List<Doctor> findBySpecializationsName(@Param("keyword") String specialtyName);

    Users findByEmail(String email);

    boolean existsByEmail(String email);
}
