package com.test.serviceImpl;

import com.test.dao.CAMMapper;
import com.test.entity.CompanyAccountManagement;
import com.test.service.CAMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CAMService")
public class CAMServiceImpl implements CAMService {

    @Autowired
    private CAMMapper camMapper;


    @Override
    public String getLogin(String camAccount) {
        return camMapper.getLogin(camAccount);
    }

    @Override
    public String getCheckEmail(String camAccount) {
        return camMapper.getCheckEmail(camAccount);
    }

    @Override
    public int LogAdd(CompanyAccountManagement companyAccountManagement) {
        return camMapper.LogAdd(companyAccountManagement);
    }

    @Override
    public int LogDeleteById(int camId) {
        return camMapper.LogDeleteById(camId);
    }

    @Override
    public List<CompanyAccountManagement> getCAMByID(int camId) {
        return camMapper.getCAMByID(camId);
    }

    @Override
    public int StaUpdateByCamAccount(String camAccount,String camPassword) {
        return camMapper.StaUpdateByCamAccount(camAccount,camPassword);
    }
}
