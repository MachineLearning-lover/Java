package com.vmi.planning.Services;

import com.vmi.planning.Dtos.PersonalDetailsDto;
import com.vmi.planning.Entities.Role;
import com.vmi.planning.Entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(User user);
    void deleteUser(Long userId);
    void addRole(Long userId, Role role);
    void deleteRole(Long userId, Long roleId);
    void addCapacity(Long userId, Long capacityId);
    void deleteCapacity(Long userId, Long capacityId);
    boolean userExists(Long userId);
    boolean userExists(String email);
    boolean userHasRole(Long userId, Long roleId);
    void updatePersonalDetails(Long userId, PersonalDetailsDto personalDetailsDto);
    Optional<User> getUser(Long userId);
    List<User> getAllUsers();
}
