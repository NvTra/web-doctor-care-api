package com.tranv.webdoctorcareapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "resetpassword")
public class ResetPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "confirmation_code")
    private String confirmationCode;

    @Column(name = "email")
    private String email;

    @Transient
    private String newPassword;

    @Transient
    private String confirmationPassword;

    @Column(name = "expiration_time")
    private LocalDateTime expirationTime;


}
