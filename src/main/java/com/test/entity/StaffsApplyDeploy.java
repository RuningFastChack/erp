package com.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffsApplyDeploy {
    private int sadId;
    private String sadNumber;
    private String sadName;
    private Timestamp sadApplyDate;
    private Timestamp sadLoseDate;
    private String sadOldDep;
    private String sadOldPos;
    private double sadOldSalary;
    private Timestamp sadEntryDate;
    @Transient
    private Department department;
    @Transient
    private Postion postion;
    private int sadNewDepId;
    private int sadNewPowId;
    private double sadNewSalary;
    private String sadParticulars;
    private String sadClass;
    private String sadPass;
    private Timestamp sadPassData;
}
