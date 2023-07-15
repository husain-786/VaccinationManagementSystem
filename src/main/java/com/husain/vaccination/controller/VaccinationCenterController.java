package com.husain.vaccination.controller;

import com.husain.vaccination.model.VaccinationCenter;
import com.husain.vaccination.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaccinationCenter")
public class VaccinationCenterController {
    @Autowired
    private VaccinationCenterService vaccinationCenterService;

    @PostMapping("/addVaccinationCenter")
    public ResponseEntity<String> addVaccinationCenter(@RequestBody VaccinationCenter vaccinationCenter){
        try{
            String str = vaccinationCenterService.addVaccinationCenter(vaccinationCenter);
            return new ResponseEntity<>(str, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
