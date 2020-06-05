package com.test.service;

import com.test.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();

    Department getById(int id);
}
