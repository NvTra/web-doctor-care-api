package com.tranv.webdoctorcareapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "training")
    private String training;

    @Column(name = "achievements")
    private String achievements;

    @OneToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "specialization_id")
    private Specializations specializations;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
