package com.test.serviceImpl;

import com.test.dao.SLCMapper;
import com.test.entity.StaffJobControl;
import com.test.entity.StaffLaborContract;
import com.test.service.SLCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SLCServiceImpl implements SLCService {

    @Autowired
    private SLCMapper slcMapper;

    @Override
    public List<StaffLaborContract> getAll() {
        return slcMapper.getAll();
    }

    @Override
    public StaffLaborContract getById(int slcId) {
        return slcMapper.getById(slcId);
    }

    @Override
    public int addSLC(StaffLaborContract staffLaborContract) {
        return slcMapper.addSLC(staffLaborContract);
    }

    @Override
    public int deleteSLC(int slcId) {
        return slcMapper.deleteSLC(slcId);
    }

    @Override
    public List<StaffLaborContract> getByNumber() {
        return slcMapper.getByNumber();
    }
}
