package com.doctor.doctorReview.Controllers;

import com.doctor.doctorReview.ServiceImpl.AppointmentServiceImpl;
import com.doctor.doctorReview.ServiceImpl.ClientServiceImpl;
import com.doctor.doctorReview.ServiceImpl.DoctorServiceImpl;
import com.doctor.doctorReview.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class Home {

    @Autowired
    ClientServiceImpl clientService;

    @Autowired
    DoctorServiceImpl doctorService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    AppointmentServiceImpl appointmentService;

    @RequestMapping(value="/",method = RequestMethod.GET)
    public String home(){

        return "hello";
    }

    @RequestMapping(value="/lo", method = RequestMethod.GET)
    public String anotherPage () {

        return "lo";
    }

}
