package com.vmi.planning.ServicesImpl;

import com.vmi.planning.Dtos.CapacityDto;
import com.vmi.planning.Dtos.PersonalDetailsDto;
import com.vmi.planning.Entities.Capacity;
import com.vmi.planning.Entities.Role;
import com.vmi.planning.Entities.User;
import com.vmi.planning.Repositories.CapacityRepository;
import com.vmi.planning.Repositories.RoleRepository;
import com.vmi.planning.Repositories.UserRepository;
import com.vmi.planning.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Strings.nullToEmpty;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private CapacityRepository capacityRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(user1 -> userRepository.delete(user1));
    }

    @Override
    public void addRole(Long userId, Role role) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            roleRepository.save(role);
            user.get().getRoles().add(role);
            userRepository.save(user.get());
        }
    }

    @Override
    public void deleteRole(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent())
        {
            if (roleRepository.findById(roleId).isPresent())
                user.get().removeRole(roleId);
        }
    }

    @Override
    public void addCapacity(Long userId, Long capacityId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Capacity> capacity = capacityRepository.findById(capacityId);
        if (user.isPresent()){
            if (capacity.isPresent()){
                user.get().getCapacities().add(capacity.get());
                userRepository.save(user.get());
            }
        }
    }

    @Override
    public void deleteCapacity(Long userId, Long capacityId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Capacity> capacity = capacityRepository.findById(capacityId);
        user.ifPresent(user1 -> capacity.ifPresent(capacity1 -> user1.getCapacities().remove(capacity1)));
    }

    @Override
    public boolean userExists(Long userId) {
        return userRepository.findById(userId).isPresent();
    }

    @Override
    public boolean userExists(String email) {
        Optional<User> user = userRepository.findByDetails_Email(email);
        return user.isPresent();
    }

    @Override
    public boolean userHasRole(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        return user.filter(user1 -> user1.getRoles().stream().map(Role::getId).filter(id -> id == roleId).count() >= 1).isPresent();
    }

    @Override
    public void updatePersonalDetails(Long userId, PersonalDetailsDto personalDetailsDto) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            user.get().getDetails().setUsername(nullToEmpty(personalDetailsDto.getUsername()));
            user.get().getDetails().setFirstName(nullToEmpty(personalDetailsDto.getFirstName()));
            user.get().getDetails().setLastName(nullToEmpty(personalDetailsDto.getLastName()));
            user.get().getDetails().setMiddleName(nullToEmpty(personalDetailsDto.getMiddleName()));
            user.get().getDetails().setUsername(nullToEmpty(personalDetailsDto.getUsername()));
            userRepository.save(user.get());
        }
    }

    @Override
    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
