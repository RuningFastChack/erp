package com.test.dao;

import com.test.entity.Department;

import java.util.List;

public interface DepartmentMapper {

    List<Department> getAll();

    Department getById(int id);
}
