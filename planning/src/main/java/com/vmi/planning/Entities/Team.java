package com.vmi.planning.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vmi.planning.Dtos.TeamDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Strings.nullToEmpty;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
public class Team {

    @Id
    @GeneratedValue
    private long id;

    @Embedded
    private TeamDetails details;

    @ManyToMany(fetch = LAZY, cascade = {PERSIST, MERGE})
    @JoinTable(name = "team_user", joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> members = new HashSet<>();

    @ManyToMany(fetch = LAZY, cascade = {PERSIST, MERGE})
    @JoinTable(name = "team_capacity", joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "capacity_id"))
    private Set<Capacity> capacities = new HashSet<>();

    @ManyToMany(fetch = LAZY, cascade = {PERSIST, MERGE}, mappedBy = "teams")
    private Set<Sprint> sprints = new HashSet<>();

    public Team(TeamDto teamDto) {
        details = new TeamDetails();
        details.setName(nullToEmpty(teamDto.getName()));
        details.setDescription(nullToEmpty(teamDto.getDescription()));
    }

    public void removeUser(Long userId){
        members = members.stream().filter(user -> user.getId() != userId).collect(Collectors.toSet());
    }


    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", details=" + details.toString() +
                ", capacities=" + capacities.toString() +
                '}';
    }


}
