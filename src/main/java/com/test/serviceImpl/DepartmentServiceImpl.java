package com.test.serviceImpl;

import com.test.dao.DepartmentMapper;
import com.test.entity.Department;
import com.test.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAll() {
        return departmentMapper.getAll();
    }

    @Override
    public Department getById(int id) {
        return departmentMapper.getById(id);
    }
}
