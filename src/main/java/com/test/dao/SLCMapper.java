package com.test.dao;

import com.test.entity.StaffJobControl;
import com.test.entity.StaffLaborContract;

import java.util.List;

public interface SLCMapper {
    List<StaffLaborContract> getAll();

    StaffLaborContract getById(int slcId);

    int addSLC(StaffLaborContract staffLaborContract);

    int deleteSLC(int slcId);

    //获取全编号
    List<StaffLaborContract> getByNumber();
}
