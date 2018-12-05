package com.vmi.planning.Entities;

import com.vmi.planning.Dtos.RoleDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.google.common.base.Strings.nullToEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
