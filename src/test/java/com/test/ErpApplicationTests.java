package com.test;

import com.test.dao.SAQMapper;
import com.test.entity.Staff;
import com.test.entity.StaffJobControl;
import com.test.entity.StaffLaborContract;
import com.test.serviceImpl.SJCServiceImpl;
import com.test.serviceImpl.SLCServiceImpl;
import com.test.serviceImpl.StaffServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ErpApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SLCServiceImpl slcService;

    @Autowired
    private SJCServiceImpl sjcService;

    @Autowired
    private StaffServiceImpl staffService;
    @Resource
    private SAQMapper saqMapper;

    @Test
    void contextLoads() {
        Date dt = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = df.format(dt);
        java.sql.Timestamp buydate = java.sql.Timestamp.valueOf(nowTime);
    }

    @Test
    void test2() {
        List<String> saqByLikeCname = saqMapper.getSAQByLikeCname("测试");
        System.out.println(saqByLikeCname);
    }

    @Test
    void test3() {
        StaffJobControl list = sjcService.getByStaffId(1);
        System.out.println(list);
    }

    @Test
    void test(){
        Staff staff = staffService.getByID(2);
        System.out.println(staff);
    }

    @Test
    void test4(){
        StaffLaborContract id = slcService.getById(1);
        System.out.println(id);
    }

}
