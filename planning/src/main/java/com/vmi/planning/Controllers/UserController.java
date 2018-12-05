package com.vmi.planning.Controllers;

import com.vmi.planning.Dtos.PersonalDetailsDto;
import com.vmi.planning.Dtos.RoleDto;
import com.vmi.planning.Dtos.UserDto;
import com.vmi.planning.Entities.Role;
import com.vmi.planning.Entities.User;
import com.vmi.planning.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/addUser", method = POST)
    public ResponseEntity addUser(@Valid @RequestBody UserDto userDto){
        //  throw error if not valid userDto

        User user = new User(userDto);

        if (!userService.userExists(userDto.getEmail())) {
            userService.saveUser(user);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.ok("Change the email please");
    }

    @RequestMapping(value="/deleteUser/{userId}", method = DELETE)
    public ResponseEntity deleteUser(@PathVariable("userId") Long id){

       if (userService.userExists(id))
            userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/addRole/{userId}", method = POST)
    public ResponseEntity addRole(@PathVariable("userId") Long id ,@Valid @RequestBody RoleDto roleDto){
        if (userService.userExists(id))
        {
           Role role = new Role(roleDto);
           userService.addRole(id, role);
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/deleteRole", method = DELETE)
    public ResponseEntity deleteRole(@RequestParam("userId") Long userId ,@RequestParam("roleId") Long roleId){
        if (userService.userHasRole(userId, roleId))
        {
            userService.deleteRole(userId, roleId);
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/addCapacity", method = POST)
    public ResponseEntity addCapacity(@RequestParam("userId") Long userId ,@RequestParam("capacityId") Long capacityId){
        if (userService.userExists(userId))
        {
            userService.addCapacity(userId, capacityId);
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/deleteCapacity", method = DELETE)
    public ResponseEntity deleteCapacity(@RequestParam("userId") Long userId ,@RequestParam("capacityId") Long capacityId){
        if (userService.userExists(userId))
        {
            userService.deleteCapacity(userId, capacityId);
        }

        return ResponseEntity.ok().build();
    }


    @RequestMapping(value="/updatePersonalDetails/{userId}", method = PUT)
    public ResponseEntity updatePersonalDetails(@PathVariable("userId") Long userId, @RequestBody PersonalDetailsDto personalDetailsDto){
        if (userService.userExists(userId))
        {
            userService.updatePersonalDetails(userId, personalDetailsDto);
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/getUser/{userId}", method = GET)
    public ResponseEntity getUser(@PathVariable("userId") Long userId){
        if (userService.userExists(userId))
        {
            return ResponseEntity.ok(userService.getUser(userId).get());
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/getAllUsers", method = GET)
    @ResponseBody
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
