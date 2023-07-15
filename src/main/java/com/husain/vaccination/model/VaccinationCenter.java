package com.husain.vaccination.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="vaccinationcenter")
public class VaccinationCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private String address;
    private int doseCapacity;

    @OneToMany(mappedBy="vaccinationCenter", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Doctor> doctorList = new ArrayList<>();


}
