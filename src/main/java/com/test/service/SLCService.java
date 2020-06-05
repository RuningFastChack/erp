package com.test.service;

import com.test.entity.StaffJobControl;
import com.test.entity.StaffLaborContract;

import java.util.List;

public interface SLCService {

    List<StaffLaborContract> getAll();

    StaffLaborContract getById(int slcId);

    int addSLC(StaffLaborContract staffLaborContract);

    int deleteSLC(int slcId);

    //获取全编号
    List<StaffLaborContract> getByNumber();
}
