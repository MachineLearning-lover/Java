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
public class Capacity {

    @Id
    @GeneratedValue
    private long id;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private int daysOff;

    private double timebox;

    private double contentbox;

    @ManyToMany(fetch = LAZY, cascade = {PERSIST, MERGE}, mappedBy = "capacities")
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = LAZY, cascade = {PERSIST, MERGE}, mappedBy = "capacities")
    private Set<Team> teams = new HashSet<>();

    @Override
    public String toString() {
        return "Capacity{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", daysOff=" + daysOff +
                ", timebox=" + timebox +
                ", contentbox=" + contentbox +
                '}';
    }
}
