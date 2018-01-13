package com.doctor.doctorReview.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Calendar start;
    private Calendar end;

    @ManyToMany(mappedBy = "holidays")
    private List<Doctor> doctors;

    public Holiday(Calendar start, Calendar end) {
        this.start = start;
        this.end = end;
    }
}
