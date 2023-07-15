package com.husain.vaccination.controller;

import com.husain.vaccination.service.Dose1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dose1")
public class Dose1Controller {
    @Autowired
    private Dose1Service dose1Service;

    @PostMapping("/giveDose1")
    public String giveDose1(@RequestParam("doseId")String doseId, @RequestParam("userId") int userId){
        return dose1Service.giveDose1(doseId, userId);
    }

    @GetMapping("/getUserName")
    public String getUserName(@RequestParam("doseId") String doseId){
        return dose1Service.getUserName(doseId);
    }
}
