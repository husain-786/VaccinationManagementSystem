package com.husain.vaccination.controller;

import com.husain.vaccination.Dto.RequestDto.AssociateDoctorDto;
import com.husain.vaccination.Dto.RequestDto.UpdateDoctorUsingEmailDto;
import com.husain.vaccination.model.Doctor;
import com.husain.vaccination.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor){
        try{
            String response = doctorService.addDoctor(doctor);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FOUND);
        }
    }

    @PostMapping("/associateDoctor")
    public ResponseEntity<String> associateDoctor(@RequestBody AssociateDoctorDto associateDoctorDto){
        try{
            String response = doctorService.associateDoctor(associateDoctorDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/findAllDoctors")
    public ResponseEntity<List<Doctor>> findAllDoctors(@RequestParam Integer centerId){
        try{
            List<Doctor> doctorList = doctorService.findAllDoctors(centerId);
            System.out.println("doctorList-controller:- "+doctorList);

            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findAllFemaleDoctors/{centerId}")
    public ResponseEntity<List<Doctor>> findAllFemaleDoctors(@PathVariable("centerId") Integer centerId){
        try{
            List<Doctor> doctorList = doctorService.findAllFemaleDoctors(centerId);
            System.out.println("doctorList-controller:- "+doctorList);

            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allAgedMaleDoctors/{centerId}")
    public ResponseEntity<List<Doctor>> allAgedMaleDoctors(@PathVariable("centerId") Integer centerId){
        try{
            List<Doctor> doctorList = doctorService.allAgedMaleDoctors(centerId);
            System.out.println("doctorList-controller:- "+doctorList);

            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/numberOfAppointmentOfADoctor")
    public ResponseEntity<List<Doctor> > numberOfAppOfADoctor(@RequestParam Integer n){
        try{
            List<Doctor> doctorList = doctorService.numberOfAppointmentOfADoctor(n);

            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allAgedMaleDoctorsWithAge/{age}")
    public ResponseEntity<List<Doctor>> allAgedMaleDoctorsWithAge(@PathVariable("age") Integer age){
        try{
            List<Doctor> doctorList = doctorService.allAgedMaleDoctorsWithAge(age);
            System.out.println("doctorList-controller:- "+doctorList);

            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ratioOfMaleAndFemaleDoctor")
    public ResponseEntity<String> ratioOfMaleAndFemaleDoctor(){
        try{
            String str = doctorService.ratioOfMaleAndFemaleDoctor();
            return new ResponseEntity<>(str, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/updateUsingEmail")
    public ResponseEntity<Doctor> updateUsingEmail(@RequestBody UpdateDoctorUsingEmailDto updateDoctorUsingEmailDto) {
        try{
            Doctor doctor = doctorService.updateUsingEmail(updateDoctorUsingEmailDto);
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
