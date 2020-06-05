package com.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Staff {
    private int sId;
    private String sJobId;
    private String sCname;
    private String sEname;
    private String sCertificateClass;
    private String sCertificateNumber;
    private String sSocialNumber;
    private String sBankNumber;
    private String sSex;
    private Date sData;
    private String sPhoneNumber;
    private String sNativePlace;
    private String sEducation;
    private String sGraduateSchool;
    private String sMajor;
    private String sPolitics;
    private String sReligion;
    private String sRegistered;
    private double sWeight;
    private double sHeight;
    private String sEmail;
    private String sMarriage;
    private String sNation;
    private String sAddress;
    private Double sVision;
    private String sHealth;
    private String sPhoto;
    private String sPass;
    private String sDep;
    private String sPos;
}
