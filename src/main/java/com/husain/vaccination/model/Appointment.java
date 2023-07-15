package com.husain.vaccination.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data   // Getter, Setter, AllARgsConstructor, NoArgsConatructor
@Entity
@Table(name="appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date appointmentDate;

    private LocalTime appointmentTime;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Doctor doctor;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;
}
