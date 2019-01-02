package com.vmi.planning.Services;

import com.vmi.planning.Entities.Team;
import com.vmi.planning.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TeamService {

    void saveTeam(Team team);
    void renameTeam(Long teamId, String newName);
    void deleteTeam(Long teamId);
    void changeTeamDescription(Long teamId, String newDescription);
    void addUserToTeam(Long teamId, Long userId);
    void removeUserFromTeam(Long teamId, Long userId);
    List<Team> getAllTeams();
    boolean teamExists(Long teamId);
    List<User> getTeamMembers(Long teamId);
}
