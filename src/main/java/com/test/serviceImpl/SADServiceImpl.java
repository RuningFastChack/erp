package com.test.serviceImpl;

import com.test.dao.SADMapper;
import com.test.entity.StaffsApplyDeploy;
import com.test.service.SADService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SADServiceImpl implements SADService {
    @Autowired
    private SADMapper sadMapper;

    @Override
    public List<StaffsApplyDeploy> getDeployAll() {
        return sadMapper.getDeployAll();
    }
}
