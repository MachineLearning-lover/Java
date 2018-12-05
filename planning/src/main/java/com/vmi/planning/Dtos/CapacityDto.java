package com.vmi.planning.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CapacityDto {

    private Date startDate;

    private Date endDate;

    private int daysOff;

    private double timebox;

    private double contentbox;
}
