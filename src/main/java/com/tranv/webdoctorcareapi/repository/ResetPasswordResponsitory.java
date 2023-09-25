package com.tranv.webdoctorcareapi.repository;

import com.tranv.webdoctorcareapi.entity.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ResetPasswordResponsitory extends JpaRepository<ResetPassword, Long> {
    ResetPassword findByConfirmationCode(String Code);

    void deleteByConfirmationCode(String code);

    void deleteByExpirationTimeBefore(LocalDateTime expirationTime);
}
