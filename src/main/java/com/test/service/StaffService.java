package com.test.service;

import com.test.entity.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffService {
    //查询
    List<Staff> getAll();
    //根据工号查询
    List<Staff> getByJobID(String sJobId);
    //删除
    int StaDeleteByID(int sId);
    //全修改
    int StaUpdateByID(Staff staff);
    //添加
    int StaAddByID(Staff staff);
    //根据中文名字查询
    List<String> getByCName(String sCname);
    //根据英文文名字查询
    List<String> getByEName(String sEname);
    //单查询
    Staff getByID(int sId);
}
