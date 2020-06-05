package com.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyAccountManagement {
    private int camId;
    private String camAccount;
    private String camEmail;
    private String camPassword;
    private int camStaffId;
    private int camRoot;
}
