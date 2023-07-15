package com.husain.vaccination.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.husain.vaccination.Enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="doctor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;

    private String name;

    private int age;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(unique = true)
    private String emailId;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private VaccinationCenter vaccinationCenter;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointment> appointmentList = new ArrayList<>();
}
