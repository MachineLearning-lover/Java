package com.vmi.planning.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalDetailsDto {

    private String username;

    private String firstName;

    private String lastName;

    private String middleName;
}
