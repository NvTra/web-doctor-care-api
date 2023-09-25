package com.tranv.webdoctorcareapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clinics")
public class Clinics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "operating_hours")
    private String operatingHours;

    @Column(name = "important_notes")
    private String importantNotes;

    @Column(name = "consultation_fee")
    private Double consultationFee;

    @Column(name = "number_appointments")
    private Integer numberAppointments;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


}
