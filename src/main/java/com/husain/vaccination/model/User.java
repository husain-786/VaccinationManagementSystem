package com.husain.vaccination.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.husain.vaccination.Enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// Enums:- is a data type that can  only take predefined values only....
//@Getter
//@Setter
@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(name="user_name")
    private String name;
    private int age;
    @Column(unique = true)
    private String emailId;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String mobileNo;

    // showing reference of the Dose1 entity class.....
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Dose1 dose1;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Appointment> appointmentList = new ArrayList<>();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Dose1 getDose1() {
        return dose1;
    }
    public void setDose1(Dose1 dose1) {
        this.dose1 = dose1;
    }
}
