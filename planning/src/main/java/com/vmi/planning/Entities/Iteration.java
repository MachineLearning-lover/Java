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

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class, property="@id")
public class Iteration {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date startIteration;

    @Temporal(TemporalType.DATE)
    private Date endIteration;

    @OneToMany(fetch = FetchType.LAZY, cascade = {PERSIST, MERGE})
    private Set<Sprint> sprints = new HashSet<>();

    @Override
    public String toString() {
        return "Iteration{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startIteration=" + startIteration +
                ", endIteration=" + endIteration +
                ", sprints=" + sprints +
                '}';
    }
}
