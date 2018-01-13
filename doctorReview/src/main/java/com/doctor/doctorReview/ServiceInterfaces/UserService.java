package com.doctor.doctorReview.ServiceInterfaces;

import com.doctor.doctorReview.Entities.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    List<User> getUsers();
    boolean emailExist(final String email);
}
