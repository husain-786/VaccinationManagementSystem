package com.husain.vaccination.service;

import com.husain.vaccination.Dto.RequestDto.AppointmentDto;
import com.husain.vaccination.Exceptions.DoctorNotFound;
import com.husain.vaccination.Exceptions.UserNotFoundException;
import com.husain.vaccination.model.Appointment;
import com.husain.vaccination.model.Doctor;
import com.husain.vaccination.model.Dose1;
import com.husain.vaccination.model.User;
import com.husain.vaccination.repo.AppointmentRepository;
import com.husain.vaccination.repo.DoctorRepository;
import com.husain.vaccination.repo.Dose1Repository;
import com.husain.vaccination.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@Service
public class AppointmentService{
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private JavaMailSender mailSender;
    public String bookAppointment(AppointmentDto appointmentDto) throws UserNotFoundException, DoctorNotFound {
        Optional<User> userOptional = userRepository.findById(appointmentDto.getUserId());
        System.out.println("User Id: "+userOptional.get().getEmailId());
        if (userOptional.isEmpty()){
            throw new UserNotFoundException("User is not present");
        }

        Optional<Doctor> doctorOptional = doctorRepository.findById(appointmentDto.getDoctorId());
        System.out.println("Doctor Id: "+doctorOptional.get().getEmailId());
        if (doctorOptional.isEmpty()){
            throw new DoctorNotFound("Doctor is not present!!!");
        }

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(new Date(2000-1900, 02, 23));
        appointment.setAppointmentTime(LocalTime.parse("11:40:30"));
        System.out.println("Date: "+appointment.getAppointmentDate() + "    Time: "+appointment.getAppointmentTime());

        Doctor doctor = doctorOptional.get();
        User user = userOptional.get();


        Appointment appntmnt = appointmentRepository.save(appointment);

        appntmnt.setUser(user);
        appntmnt.setDoctor(doctor);

        user.getAppointmentList().add(appntmnt);
        doctor.getAppointmentList().add(appntmnt);

        appointmentRepository.save(appntmnt);

        // *******************Sending Mail to the user*********************
        String body = String.format(("Hi, %s \nYou have successfully booked an appointment on %s. \nYor Doctor is %s. " +
                        "\nPlease reach at %s. \nMask is Mandatory!!!!!"),
                user.getName(), appointment.getAppointmentDate(), doctor.getName(), doctor.getVaccinationCenter().getAddress());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("husainnetwin@gmail.com");
        //mailMessage.setTo(user.getEmailId());
        mailMessage.setTo("sajjad.husain.s.p@gmail.com");
        mailMessage.setSubject("Appointment Confirmed!!!!!");
        mailMessage.setText(body);

        mailSender.send(mailMessage);

        return "Doctor Appointed to the user successfully!!!!";
    }
}