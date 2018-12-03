package com.vmi.planning.ServicesImpl;

import com.vmi.planning.Entities.Team;
import com.vmi.planning.Entities.User;
import com.vmi.planning.Repositories.TeamRepository;
import com.vmi.planning.Repositories.UserRepository;
import com.vmi.planning.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void renameTeam(Long teamId, String newName) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()){
            team.get().getDetails().setName(newName);
            teamRepository.save(team.get());
        }
    }


    @Override
    public void deleteTeam(Long teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()){
            teamRepository.delete(team.get());
        }
    }

    @Override
    public void changeTeamDescription(Long teamId, String newDescription) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()){
            team.get().getDetails().setDescription(newDescription);
            teamRepository.save(team.get());
        }
    }

    @Override
    public void addUserToTeam(Long teamId, Long userId) {
        Optional<Team> team = teamRepository.findById(teamId);
        Optional<User> user = userRepository.findById(userId);
        if (team.isPresent()){
            if (user.isPresent()) {
                team.get().getMembers().add(user.get());
                teamRepository.save(team.get());
//                user.get().getTeams().add(team.get());
//                userRepository.save(user.get());
            }
        }
    }

    @Override
    public void removeUserFromTeam(Long teamId, Long userId) {
        Optional<Team> team = teamRepository.findById(teamId);
        Optional<User> user = userRepository.findById(userId);
        if (team.isPresent()){
            if (user.isPresent()) {
                team.get().removeUser(userId);
                teamRepository.save(team.get());
            }
        }
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public boolean teamExists(Long teamId) {
        return teamRepository.findById(teamId).isPresent();
    }
}
