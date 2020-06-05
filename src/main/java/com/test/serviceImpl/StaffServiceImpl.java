package com.test.serviceImpl;

import com.test.dao.StaffMapper;
import com.test.entity.Staff;
import com.test.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService{
    @Autowired
    private StaffMapper staffMapper;


    @Override
    public List<Staff> getAll() {
        return staffMapper.getAll();
    }

    @Override
    public List<Staff> getByJobID(String sJobId) {
        return staffMapper.getByJobID(sJobId);
    }

    @Override
    public int StaDeleteByID(int sId) {
        return staffMapper.StaDeleteByID(sId);
    }

    @Override
    public int StaUpdateByID(Staff staff) {
        return staffMapper.StaUpdateByID(staff);
    }

    @Override
    public int StaAddByID(Staff staff) {
        return staffMapper.StaAddByID(staff);
    }

    @Override
    public List<String> getByCName(String sCname) {
        return staffMapper.getByCName(sCname);
    }

    @Override
    public List<String> getByEName(String sEname) {
        return staffMapper.getByEName(sEname);
    }

    @Override
    public Staff getByID(int sId) {
        return staffMapper.getByID(sId);
    }

}
