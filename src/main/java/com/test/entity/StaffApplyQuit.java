package com.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffApplyQuit {
    private int saqId;
    private String saqNumber;
    private String saqTitle;
    private int saqNameId;
    private Timestamp saqApplyDate;
    private Timestamp saqLoseDate;
    private Timestamp saqEfficaciousDate;
    private String saqClass;
    private String saqParticulars;
    private String saqNowDep;
    private String saqNowPos;
    private Timestamp saqEntryDate;
    private String saqStaffClass;
    private String saqPass;
    @Transient
    private Staff staff;

    public StaffApplyQuit(int saqId, String saqNumber, String saqTitle, int saqNameId, Timestamp saqApplyDate, Timestamp saqLoseDate, Timestamp saqEfficaciousDate, String saqClass, String saqParticulars, String saqNowDep, String saqNowPos, Timestamp saqEntryDate, String saqStaffClass, String saqPass) {
        this.saqId = saqId;
        this.saqNumber = saqNumber;
        this.saqTitle = saqTitle;
        this.saqNameId = saqNameId;
        this.saqApplyDate = saqApplyDate;
        this.saqLoseDate = saqLoseDate;
        this.saqEfficaciousDate = saqEfficaciousDate;
        this.saqClass = saqClass;
        this.saqParticulars = saqParticulars;
        this.saqNowDep = saqNowDep;
        this.saqNowPos = saqNowPos;
        this.saqEntryDate = saqEntryDate;
        this.saqStaffClass = saqStaffClass;
        this.saqPass = saqPass;
    }
}
