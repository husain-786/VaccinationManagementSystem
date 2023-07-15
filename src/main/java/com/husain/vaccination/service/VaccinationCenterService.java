package com.husain.vaccination.service;

import com.husain.vaccination.Exceptions.VaccinationCenterAddressNotFound;
import com.husain.vaccination.model.VaccinationCenter;
import com.husain.vaccination.repo.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VaccinationCenterService {
    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;
    public String addVaccinationCenter(VaccinationCenter vaccinationCenter) throws VaccinationCenterAddressNotFound{
        if (vaccinationCenter.getAddress() == null){
            throw new VaccinationCenterAddressNotFound("Vaccination Center Address cannot be Empty!!!!");
        }
        vaccinationCenterRepository.save(vaccinationCenter);
        return "Vaccination Center Added Successfully at location "+vaccinationCenter.getAddress();
    }
}
