package com.vmi.planning.Entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamDetails {

    @NotNull
    @NotEmpty
    private String name;

    private String description;
}
