package com.test.dao;

import com.test.entity.Postion;

import java.util.List;

public interface PostionMapper {

    List<Postion> getPostionAll();

    Postion getById(int id);
}
