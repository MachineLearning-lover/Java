package com.vmi.planning.Entities;

import com.vmi.planning.Dtos.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.google.common.base.Strings.nullToEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @NotEmpty
    @Size(min = 1)
    private String name;

    private String description;

    public Role(RoleDto roleDto) {
        name = nullToEmpty(roleDto.getName());
        description = nullToEmpty(roleDto.getDescription());
    }
}
