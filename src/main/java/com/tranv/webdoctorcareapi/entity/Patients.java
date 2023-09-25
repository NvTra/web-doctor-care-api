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
@Table(name = "patients")
public class Patients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "users_id")
    private Users users;

    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "patients")
    private List<Appointment> appointments;

}
