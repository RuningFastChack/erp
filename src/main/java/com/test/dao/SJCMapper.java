package com.test.dao;

import com.test.entity.StaffJobControl;

import java.util.List;
import java.util.Map;

public interface SJCMapper {
    List<StaffJobControl> getAll();

    int deleteSJC(int sjcId);

    int addSJC(StaffJobControl staffJobControl);

    //获取全编号
    List<StaffJobControl> getByNumber();

    StaffJobControl getByStaffId(int sjcStaffId);
}
