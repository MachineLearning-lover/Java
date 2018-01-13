package com.doctor.doctorReview.ServiceImpl;

import com.doctor.doctorReview.Entities.User;
import com.doctor.doctorReview.Repositories.UserRepository;
import com.doctor.doctorReview.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
