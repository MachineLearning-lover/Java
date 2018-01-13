package com.doctor.doctorReview.Controllers;

import com.doctor.doctorReview.DTO.UserDto;
import com.doctor.doctorReview.Errors.UserAlreadyExistException;
import com.doctor.doctorReview.Misc.UserFactory;
import com.doctor.doctorReview.ServiceImpl.ClientServiceImpl;
import com.doctor.doctorReview.ServiceImpl.DoctorServiceImpl;
import com.doctor.doctorReview.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class Registration {

    @Autowired
    ClientServiceImpl clientService;

    @Autowired
    DoctorServiceImpl doctorService;

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/user/registration",method = RequestMethod.POST)
    @ResponseBody
    public String UserRegistration(@Valid final UserDto userDto){
        if (userService.emailExist(userDto.getEmail()) == false) {
            UserFactory userFactory = new UserFactory(userDto);
            if (userDto.getUserType().equalsIgnoreCase("doctor")) {
                doctorService.saveDoctor(userFactory.getDoctor());
            }

            if (userDto.getUserType().equalsIgnoreCase("client")) {
                clientService.saveClient(userFactory.getClient());
            }
        }
        else
        {
            throw new UserAlreadyExistException("user already in DB");
        }
        return "success";
    }

    @RequestMapping(value = "/user/registration",method = RequestMethod.GET)
    public String UserRegistrationGreetings(){
        return "welcome";
    }
}
