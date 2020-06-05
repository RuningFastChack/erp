package com.test.service;


import com.test.entity.CompanyAccountManagement;

import java.util.List;

public interface CAMService {
    //登录
    String getLogin(String camAccount);

    //查询邮箱
    String getCheckEmail(String camAccount);

    //注册
    int LogAdd(CompanyAccountManagement companyAccountManagement);

    //注销
    int LogDeleteById(int camId);

    //根据ID查询账号
    List<CompanyAccountManagement> getCAMByID(int camId);

    //修改，忘记密码之类的(根据账号)
    int StaUpdateByCamAccount(String camAccount, String camPassword);
}
