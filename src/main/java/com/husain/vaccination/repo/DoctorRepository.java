package com.husain.vaccination.repo;

import com.husain.vaccination.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    public Doctor findByEmailId(String emailId);

    @Query(value = "select * from doctor where vaccination_center_id = :centerId and age>=40", nativeQuery = true)
    List<Doctor> findAllAgedDoctors(Integer centerId);
}
