package com.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffLaborContract {
    private int slcId;
    private String slcNumber;
    private String slcName;
    private Timestamp slcSignDate;
    private Timestamp slcLoseDate;
    private Timestamp slcEfficaciousDate;

    @Transient
    private Staff Staff;
    @Transient
    private String signDate;
    @Transient
    private String loseDate;
    @Transient
    private String efficaciousDate;

    private int slcStaffId;
    private String slcClass;

    public StaffLaborContract(String slcNumber, String slcName, Timestamp slcSignDate, Timestamp slcLoseDate, Timestamp slcEfficaciousDate, int slcStaffId, String slcClass) {
        this.slcNumber = slcNumber;
        this.slcName = slcName;
        this.slcSignDate = slcSignDate;
        this.slcLoseDate = slcLoseDate;
        this.slcEfficaciousDate = slcEfficaciousDate;
        this.slcStaffId = slcStaffId;
        this.slcClass = slcClass;
    }

    public void setSignDate(Timestamp signDate) {
        String timeStr=signDate
                .toString()
                .substring(0, signDate.toString().indexOf("."));
        this.signDate = timeStr;
    }

    public void setLoseDate(Timestamp loseDate) {
        String timeStr=loseDate
                .toString()
                .substring(0, loseDate.toString().indexOf("."));
        this.loseDate = timeStr;
    }

    public void setEfficaciousDate(Timestamp efficaciousDate) {
        String timeStr=efficaciousDate
                .toString()
                .substring(0, efficaciousDate.toString().indexOf("."));
        this.efficaciousDate = timeStr;
    }
}
