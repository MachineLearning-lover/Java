package com.vmi.planning.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalDetails {

    @Size(min = 6)
    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private String middleName;
}
