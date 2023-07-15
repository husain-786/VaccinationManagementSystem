package com.husain.vaccination.repo;

import com.husain.vaccination.model.Dose1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Dose1Repository extends JpaRepository<Dose1, Integer> {
//    @Query("select * from Dose1 d where :=d.doseId=doseId")
//    Dose1 getByDoseId(String doseId);

    Dose1 findByDoseId(String doseId);
}
