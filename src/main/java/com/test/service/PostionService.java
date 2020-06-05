package com.test.service;

import com.test.entity.Postion;

import java.util.List;

public interface PostionService {

    List<Postion> getPostionAll();

    Postion getById(int id);
}
