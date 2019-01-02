package com.vmi.planning.Controllers;

import com.vmi.planning.Dtos.TeamDto;
import com.vmi.planning.Entities.Team;
import com.vmi.planning.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/addTeam", method = POST)
    public ResponseEntity addTeam(@RequestBody TeamDto teamDto){
        Team team = new Team(teamDto);
        teamService.saveTeam(team);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value= "/getAllTeams", method = GET)
    public List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }

    @RequestMapping(value = "/renameTeam/{id}", method = PUT)
    public ResponseEntity renameTeam(@PathVariable("id") Long id, @RequestParam("newName") String newName){
        if (teamService.teamExists(id))
            teamService.renameTeam(id, newName);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/deleteTeam/{id}", method = DELETE)
    public ResponseEntity deleteTeam(@PathVariable("id") Long id){
        if (teamService.teamExists(id))
            teamService.deleteTeam(id);

        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/renameTeamDescription/{id}", method = PUT)
    public ResponseEntity changeTeamDescription(@PathVariable("id") Long id,
                                                @RequestParam("newDescription") String newDescription){
        if (teamService.teamExists(id))
            teamService.changeTeamDescription(id, newDescription);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/addUser", method = POST)
    public ResponseEntity addUserToTeam(@RequestParam("userId") Long userId, @RequestParam("teamId") Long teamId){
        if (teamService.teamExists(teamId)){
            teamService.addUserToTeam(teamId, userId);
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/removeUser", method = DELETE)
    public ResponseEntity removeUserFromTeam(@RequestParam("userId") Long userId, @RequestParam("teamId") Long teamId){
        if (teamService.teamExists(teamId)){
            teamService.removeUserFromTeam(teamId, userId);
        }

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/getMemebers/{teamId}", method = GET)
    public ResponseEntity getUsersFromTeam(@PathVariable("teamId") Long teamId){
        return ResponseEntity.ok(teamService.getTeamMembers(teamId));
    }
}
