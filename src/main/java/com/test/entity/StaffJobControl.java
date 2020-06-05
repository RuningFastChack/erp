package com.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffJobControl {
    private int sjcId;
    private String sjcNumber;
    @Transient
    private Staff staff;
    @Transient
    private Postion postion;
    @Transient
    private Department department;
    @Transient
    private StaffLaborContract staffLaborContract;

    private int sjcStaffId;
    private int sjcPosId;
    private int sjcDepId;
    private int sjcSlcId;
    private double sjcNowSalary;
    private String sjcClass;
}
