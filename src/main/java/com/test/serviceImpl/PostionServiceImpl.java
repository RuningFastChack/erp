package com.test.serviceImpl;

import com.test.dao.PostionMapper;
import com.test.entity.Postion;
import com.test.service.PostionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostionServiceImpl implements PostionService {

    @Autowired
    private PostionMapper postionMapper;

    @Override
    public List<Postion> getPostionAll() {
        return postionMapper.getPostionAll();
    }

    @Override
    public Postion getById(int id) {
        return postionMapper.getById(id);
    }
}
