package com.vmi.planning.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
