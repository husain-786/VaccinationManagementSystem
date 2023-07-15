package com.husain.vaccination.service;

import com.husain.vaccination.model.Dose1;
import com.husain.vaccination.model.User;
import com.husain.vaccination.repo.Dose1Repository;
import com.husain.vaccination.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Dose1Service {
    @Autowired
    private Dose1Repository dose1Repository;
    @Autowired
    private UserRepository userRepository;
    public String giveDose1(String doseId, int userId) {
        Dose1 dose = new Dose1();

        dose.setDoseId(doseId);

        // userRepository.findById(userId):- returns optional, so we need use .get() to get actual data.......
        Optional<User> res = userRepository.findById(userId);
        User user = res.get();
//        User user = userRepository.findById(userId).get();

        // Sets the reference of parent in child (i.e., Stores the foreign key with reference to the user)......
        dose.setUser(user);

        // setting the child object(dose1) reference in user......
        user.setDose1(dose);

        dose1Repository.save(dose);
        // child will automatically get saved due to cascading effect.......
        return "Dose given to user successfully susseccfully!!!";
    }

    public String getUserName(String doseId) {
        Dose1 dose = dose1Repository.findByDoseId(doseId);
        return dose.getUser().getName();
    }
}
