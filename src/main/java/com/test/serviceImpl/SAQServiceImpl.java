package com.test.serviceImpl;

import com.test.dao.SAQMapper;
import com.test.entity.StaffApplyQuit;
import com.test.service.SAQService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SAQServiceImpl implements SAQService {
    @Resource
    private SAQMapper saqMapper;
    @Override
    public List<StaffApplyQuit> getAll() {
        return saqMapper.getAll();
    }

    @Override
    public List<StaffApplyQuit> getSAQById(int saqId) {
        return saqMapper.getSAQById(saqId);
    }

    @Override
    public List<String> getSAQByLikeCname(String CName) {
        return saqMapper.getSAQByLikeCname(CName);
    }

    @Override
    public List<StaffApplyQuit> getSAQByNumber(String saqNumber) {
        return saqMapper.getSAQByNumber(saqNumber);
    }

    @Override
    public int SAQUpdateByID(StaffApplyQuit staffApplyQuit) {
        return saqMapper.SAQUpdateByID(staffApplyQuit);
    }

    @Override
    public int SAQAddByID(StaffApplyQuit staffApplyQuit) {
        return saqMapper.SAQAddByID(staffApplyQuit);
    }

    @Override
    public int SAQDeleteById(int saqId) {
        return saqMapper.SAQDeleteById(saqId);
    }
}
