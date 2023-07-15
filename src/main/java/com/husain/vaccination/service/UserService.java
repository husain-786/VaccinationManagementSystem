package com.husain.vaccination.service;

import com.husain.vaccination.Dto.RequestDto.UpdateEmailDto;
import com.husain.vaccination.model.User;
import com.husain.vaccination.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Optional<User> addUser(User user) {
        try {
            userRepository.save(user);
            return Optional.of(user);
        }
        catch(Exception e){
            System.out.println("Error:- "+e);
            return Optional.empty();
        }
    }

    public Date getVaccinationDate(int userId) {
        User user = userRepository.getById(userId);
        return user.getDose1().getVaccinationDate();
    }

    public String updateEmail(UpdateEmailDto updateEmailDto) {
        int userId = updateEmailDto.getUserId();

        User user = userRepository.getById(userId);
        user.setEmailId(updateEmailDto.getNewEmail());

        userRepository.save(user);

        return "New email is updated successfully with "+updateEmailDto.getNewEmail();
    }

    public User getUserByEmail(String email) {
        try {
            User user = userRepository.findByEmailId(email);
            System.out.println(user.getUserId() + "===>" + user.getEmailId());
            return user;
        }
        catch(Exception e){
            System.out.println("Error:- " + e);
            return null;
        }
//        finally {
//            return null;
//        }
    }
}
