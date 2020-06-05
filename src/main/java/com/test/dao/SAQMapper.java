package com.test.dao;

import com.test.entity.StaffApplyQuit;

import java.util.List;

public interface SAQMapper {
    //全查询
    List<StaffApplyQuit> getAll();
    //根据ID查询
    List<StaffApplyQuit> getSAQById(int saqId);
    //模糊查询名字获得表
    List<String> getSAQByLikeCname(String CName);
    //查询合同编号
    List<StaffApplyQuit> getSAQByNumber(String saqNumber);
    //修改
    int SAQUpdateByID(StaffApplyQuit staffApplyQuit);
    //添加
    int SAQAddByID(StaffApplyQuit staffApplyQuit);
    //删除
    int SAQDeleteById(int saqId);
}