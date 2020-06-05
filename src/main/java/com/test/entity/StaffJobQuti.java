package com.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffJobQuti {
    private int sjqId;
    private String sjqNumber;
    private int sjqStaffId;
    private String sjqPos;
    private String sjqDep;
    private Timestamp sjqEntryDate;
    private Timestamp sjqDimDate;
    private String sjqClass;
    private String sjqCause;
    private String sjqAccount;
    private String sjqPassword;
    private int sjqHidden;
}
