package com.husain.vaccination.service;

import com.husain.vaccination.Dto.RequestDto.AssociateDoctorDto;
import com.husain.vaccination.Dto.RequestDto.UpdateDoctorUsingEmailDto;
import com.husain.vaccination.Enums.Gender;
import com.husain.vaccination.Exceptions.DoctorAlreadyExistsException;
import com.husain.vaccination.Exceptions.DoctorNotFound;
import com.husain.vaccination.Exceptions.EmailIdEmptyException;
import com.husain.vaccination.Exceptions.VaccinationCenterAddressNotFound;
import com.husain.vaccination.model.Doctor;
import com.husain.vaccination.model.VaccinationCenter;
import com.husain.vaccination.repo.DoctorRepository;
import com.husain.vaccination.repo.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;
    public String addDoctor(Doctor doctor) throws DoctorAlreadyExistsException, EmailIdEmptyException {
        if (doctor.getEmailId() == null){
            throw new EmailIdEmptyException("Email Not Found!!!!");
        }
        if (doctorRepository.findByEmailId(doctor.getEmailId()) != null){
            throw new DoctorAlreadyExistsException("Doctor with this email is already present!!!");
        }
        doctorRepository.save(doctor);

        return "Doctor has been added to the database!!!";
    }

    public String associateDoctor(AssociateDoctorDto associateDoctorDto) throws DoctorNotFound, VaccinationCenterAddressNotFound {
        int doctorId = associateDoctorDto.getDoctorId();
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if (doctorOptional.isEmpty()){
            throw new DoctorNotFound("Doctor Id is wrrong!!!");
        }

        int centerId = associateDoctorDto.getVaccinationCenterId();
        Optional<VaccinationCenter> vaccinationCenterOptional = vaccinationCenterRepository.findById(centerId);
        if (vaccinationCenterOptional.isEmpty()){
            throw new VaccinationCenterAddressNotFound("Vaccination Center do not Exist!!!");
        }

        Doctor doctor = doctorOptional.get();
        VaccinationCenter vaccinationCenter = vaccinationCenterOptional.get();

        doctor.setVaccinationCenter(vaccinationCenter);

        // setting bidirectional....
        // adding doctor in the list of doctr in vaccination center.....
        vaccinationCenter.getDoctorList().add(doctor);

        vaccinationCenterRepository.save(vaccinationCenter);

        return "Doctor has been asscociated with the Vaccination Center!!!";
    }

    public List<Doctor> findAllDoctors(Integer centerId) throws VaccinationCenterAddressNotFound{
        Optional<VaccinationCenter> vaccinationCenterOptional = vaccinationCenterRepository.findById(centerId);
        if (vaccinationCenterOptional.isEmpty()){
            throw new VaccinationCenterAddressNotFound("Center Not Found!!!");
        }

        VaccinationCenter vaccinationCenter = vaccinationCenterOptional.get();

        List<Doctor> doctorList = vaccinationCenter.getDoctorList();

        System.out.println("doctorList:- "+doctorList);

        return doctorList;
    }

    public List<Doctor> findAllFemaleDoctors(Integer centerId) throws VaccinationCenterAddressNotFound{
        Optional<VaccinationCenter> vaccinationCenterOptional = vaccinationCenterRepository.findById(centerId);
        if (vaccinationCenterOptional.isEmpty()){
            throw new VaccinationCenterAddressNotFound("Center Not Found!!!");
        }

        VaccinationCenter vaccinationCenter = vaccinationCenterOptional.get();
        List<Doctor> doctorList = vaccinationCenter.getDoctorList();

        List<Doctor> ans = new ArrayList<>();
        for (Doctor doctor: doctorList){
            if (doctor.getGender().name().equalsIgnoreCase(Gender.FEMALE.name())){
                ans.add(doctor);
            }
        }

        return ans;
    }

    public List<Doctor> allAgedMaleDoctors(Integer centerId) throws VaccinationCenterAddressNotFound{
        Optional<VaccinationCenter> vaccinationCenterOptional = vaccinationCenterRepository.findById(centerId);
        if (vaccinationCenterOptional.isEmpty()){
            throw new VaccinationCenterAddressNotFound("Center Not Found!!!");
        }

        VaccinationCenter vaccinationCenter = vaccinationCenterOptional.get();
        List<Doctor> doctorList = vaccinationCenter.getDoctorList();

//        // Method:1=> with logic
//        List<Doctor> ans = new ArrayList<>();
//        for (Doctor doctor: doctorList){
//            if (doctor.getGender().name().equalsIgnoreCase(Gender.MALE.name()) && doctor.getAge() >= 40){
//                ans.add(doctor);
//            }
//        }

        // Method:2=> Using Query....
        List<Doctor> ans = doctorRepository.findAllAgedDoctors(centerId);
        return ans;
    }

    public List<Doctor> numberOfAppointmentOfADoctor(Integer n) {
        List<Doctor> doctorList = doctorRepository.findAll();
        List<Doctor> ans = new ArrayList<>();

        // Method:-1=> Using Logic....
        for (Doctor doctor: doctorList){
            if (doctor.getAppointmentList().size() >=n){
                ans.add(doctor);
            }
        }

        return ans;
    }

    public List<Doctor> allAgedMaleDoctorsWithAge(Integer age) {
        List<Doctor> doctorList = doctorRepository.findAll();

        // Method:1=> with logics
        List<Doctor> ans = new ArrayList<>();
        for (Doctor doctor: doctorList){
            if (doctor.getGender().name().equalsIgnoreCase(Gender.MALE.name()) && doctor.getAge() >= age){
                ans.add(doctor);
            }
        }

        // Method:2=> Using Query....
//        List<Doctor> ans = doctorRepository.findAllAgedDoctors(age);
        return ans;
    }

    public String ratioOfMaleAndFemaleDoctor() throws Exception {
         List<Doctor> doctorList = doctorRepository.findAll();

         if (doctorList.size() == 0){
             return "No Doctors are Present!!!";
         }

         int n = doctorList.size(), nFemale = 0, nMale = 0;

         for (Doctor doctor: doctorList){
             if (doctor.getGender().name().equalsIgnoreCase(Gender.FEMALE.name())){
                 nFemale++;
             }
             else{
                 nMale++;
             }
         }

         Double maleRatio = (double)nMale/n;
         Double femaleRatio = (double)nFemale/n;

         return "Male:- " + maleRatio + "\nFemale:- "+femaleRatio;
    }

    public Doctor updateUsingEmail(UpdateDoctorUsingEmailDto updateDoctorUsingEmailDto) throws DoctorNotFound{
        Doctor doctor = doctorRepository.findByEmailId(updateDoctorUsingEmailDto.getEmailId());
        if (doctor == null){
            throw new DoctorNotFound("Doctor with the given Email " + "'" + updateDoctorUsingEmailDto.getEmailId() + "'" + "is not present!!!");
        }

        doctor.setAge(updateDoctorUsingEmailDto.getAge());
        doctor.setName(updateDoctorUsingEmailDto.getName());

        return doctorRepository.save(doctor);
    }
}
