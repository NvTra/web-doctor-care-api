package com.tranv.webdoctorcareapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Specializations")
public class Specializations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "number_choose")
    private Integer numberChoose;

    @Column(name = "number_search")
    private Integer numberSearch;

    @OneToMany(mappedBy = "specializations")
    @JsonIgnore
    private List<Doctor> doctorList;
}

