package com.test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.entity.Staff;
import com.test.entity.StaffJobControl;
import com.test.entity.StaffLaborContract;
import com.test.serviceImpl.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/sjc")
public class SJCController {

    @Autowired
    private SJCServiceImpl sjcService;

    @Autowired
    private StaffServiceImpl staffService;

    @Autowired
    private DepartmentServiceImpl departmentService;

    @Autowired
    private PostionServiceImpl postionService;

    @Autowired
    private SLCServiceImpl slcService;

    @RequestMapping("/test")
    public String test(Model model,
                           @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                           @RequestParam(defaultValue="1",value="pageSize")Integer pageSize){
        //为了程序的严谨性，判断非空：
        //设置默认当前页
        if(pageNum==null || pageNum<=0){
            pageNum = 1;
        }
        //设置默认每页显示的数据数
        if(pageSize == null){
            pageSize = 1;
        }
        System.out.println("当前页是："+pageNum+"显示条数是："+pageSize);

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum,pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<StaffJobControl> list = sjcService.getAll();
            for (StaffJobControl s :
                    list) {
                StaffLaborContract laborContract = s.getStaffLaborContract();
                laborContract.setSignDate(laborContract.getSlcSignDate());
                laborContract.setEfficaciousDate(laborContract.getSlcEfficaciousDate());
                laborContract.setLoseDate(laborContract.getSlcLoseDate());
                s.setStaffLaborContract(laborContract);
            }
            System.out.println("分页数据："+list);
            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<StaffJobControl> pageInfo = new PageInfo<StaffJobControl>(list,pageSize);
            //4.使用model传参数回前端
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("list",list);
        }finally {
            //清理 ThreadLocal 存储的分页参数,保证线程安全
            PageHelper.clearPage();
        }
        return "test";
    }

    @RequestMapping("/deleteSJC")
    public String deleteSJC(Model model,int sjcId,Integer pageNum,Integer pageSize){
        int i = sjcService.deleteSJC(sjcId);
        if (i>0)
            return test(model, pageNum, pageSize);
        else
            model.addAttribute("msg","删除失败,请重新尝试!");
        return index();
    }

    @RequestMapping("/addSJC")
    public String addSJC(Model model){
        Random random = new Random();
        String num="RLZY"+random.nextInt(1000) + 1;
        List<StaffJobControl> number = sjcService.getByNumber();
        for(int j=0;j<number.size();j++){
            StaffJobControl staffJobControl = number.get(j);
            String sjcNumber = staffJobControl.getSjcNumber();
            if(sjcNumber.equals(num)){
                num="RLZY"+random.nextInt(1000) + 1;
                j=0;
            }
        }
        model.addAttribute("staff",staffService.getAll());
        model.addAttribute("department",departmentService.getAll());
        model.addAttribute("postion",postionService.getPostionAll());
        model.addAttribute("slc",slcService.getAll());
        model.addAttribute("sjcNumber",num);
        return "addSJC";
    }

    @RequestMapping("/add")
    public String add(StaffJobControl staffJobControl){
        Staff staff = staffService.getByID(staffJobControl.getSjcStaffId());
        StaffLaborContract staffLaborContract = new StaffLaborContract();
        if (staff.getSPass().equals("是")){
            Random random = new Random();
            String num="LDHT"+random.nextInt(1000) + 1;
            List<StaffLaborContract> number = slcService.getByNumber();
            for(int j=0;j<number.size();j++){
                StaffLaborContract slc = number.get(j);
                String slcNumber = slc.getSlcNumber();
                if(slcNumber.equals(num)){
                    num="LDHT"+random.nextInt(1000) + 1;
                    j=0;
                }
            }
            staffLaborContract.setSlcNumber(num);
            staffLaborContract.setSlcName(staff.getSCname()+staffJobControl.getSjcClass()+"合同");
        }
        return index();
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
