package com.husain.vaccination.controller;

import com.husain.vaccination.Dto.RequestDto.UpdateEmailDto;
import com.husain.vaccination.model.User;
import com.husain.vaccination.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        Optional<User> response = userService.addUser(user);
        if (response.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response.get(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getVaccinationDate")
    public Date getVaccinationDate(@RequestParam("userId") int userId){
        return userService.getVaccinationDate(userId);
    }

    @PutMapping("/updateEmail")
    public String updateEmail(@RequestBody UpdateEmailDto updateEmailDto){
        return userService.updateEmail(updateEmailDto);
    }

//    @GetMapping("/getUserByEmail/{emailId}")
//    public User getUserByEmail(@PathVariable("emailId") String email){
//        return userService.getUserByEmail(email);
//    }

    @GetMapping("/getUserByEmail")
    public User getUserByEmail(@RequestParam("emailId") String email){
        User user = userService.getUserByEmail(email);
        System.out.println(user);
        return user;
    }
}
