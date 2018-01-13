package com.doctor.doctorReview.Misc;

import com.doctor.doctorReview.DTO.UserDto;
import com.doctor.doctorReview.Entities.Client;
import com.doctor.doctorReview.Entities.Doctor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserFactory {

    private UserDto userDto;

    public UserFactory() {
    }

    public UserFactory(UserDto userDto) {
        this.userDto = userDto;
    }

    public Client getClient(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return new Client(userDto.getFirstName(),userDto.getLastName(),userDto.getMiddleName(),userDto.getEmail(),
                bCryptPasswordEncoder.encode(userDto.getPassword()));
    }

    public Doctor getDoctor(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return new Doctor(userDto.getFirstName(),userDto.getLastName(),userDto.getMiddleName(),userDto.getEmail(),
                bCryptPasswordEncoder.encode(userDto.getPassword()));
    }

}
