package com.test.serviceImpl;

import com.test.dao.SJCMapper;
import com.test.entity.StaffJobControl;
import com.test.service.SJCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class SJCServiceImpl implements SJCService {

    @Autowired
    private SJCMapper sjcMapper;

    @Override
    public List<StaffJobControl> getAll() {
        return sjcMapper.getAll();
    }

    @Override
    public int deleteSJC(int sjcId) {
        return sjcMapper.deleteSJC(sjcId);
    }

    @Override
    public List<StaffJobControl> getByNumber() {
        return sjcMapper.getByNumber();
    }

    @Override
    public int addSJC(StaffJobControl staffJobControl) {
        return sjcMapper.addSJC(staffJobControl);
    }

    @Override
    public StaffJobControl getByStaffId(int sjcStaffId) {
        return sjcMapper.getByStaffId(sjcStaffId);
    }
}
