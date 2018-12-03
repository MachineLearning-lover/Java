package com.vmi.planning.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vmi.planning.Dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.google.common.base.Strings.nullToEmpty;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Embedded
    private PersonalDetails details = new PersonalDetails();

    @OneToMany(fetch = EAGER)
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = LAZY, cascade = {MERGE, PERSIST}, mappedBy = "members")
    private Set<Team> teams = new HashSet<>();

    @ManyToMany(fetch = LAZY, cascade = {MERGE, PERSIST})
    @JoinTable(name = "user_capacity", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "capacity_id"))
    private Set<Capacity> capacities = new HashSet<>();

    public User(UserDto userDto) {
        details.setEmail(nullToEmpty(userDto.getEmail()));
        details.setUsername(nullToEmpty(userDto.getUsername()));
    }

    public void removeRole(String roleName){
        roles = roles.stream().filter(role -> !role.getName().equalsIgnoreCase(roleName)).collect(Collectors.toSet());
    }

    public void removeRole(Long roleId){
        roles = roles.stream().filter(role -> role.getId() != roleId).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", details=" + details +
                ", roles=" + roles +
                ", teams=" + teams.toString() +
                ", capacities=" + capacities +
                '}';
    }
}
