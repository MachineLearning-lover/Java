package com.vmi.planning.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
public class Sprint {

    @Id
    @GeneratedValue
    private long id;

    @ManyToMany(fetch = LAZY, cascade = {MERGE, PERSIST})
    @JoinTable(name = "sprint_team", joinColumns = @JoinColumn(name = "sprint_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> teams = new HashSet<>();

    @Temporal(TemporalType.DATE)
    private Date startSprint;

    @Temporal(TemporalType.DATE)
    private Date endSprint;

    private String name;

    @Override
    public String toString() {
        return "Sprint{" +
                "id=" + id +
                ", teams=" + teams +
                ", startSprint=" + startSprint +
                ", endSprint=" + endSprint +
                ", name='" + name + '\'' +
                '}';
    }
}
