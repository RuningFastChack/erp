package com.test.service;

import com.test.entity.StaffJobControl;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface SJCService {

    List<StaffJobControl> getAll();

    int deleteSJC(int sjcId);

    List<StaffJobControl> getByNumber();

    int addSJC(StaffJobControl staffJobControl);

    StaffJobControl getByStaffId(int sjcStaffId);
}
