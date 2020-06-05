package com.test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.dao.StaffMapper;
import com.test.entity.Staff;
import com.test.entity.StaffApplyQuit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Resource
    private StaffMapper staffMapper;

    /**
     * 分页全查询
     * @param model
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/StaAll")
    public String getAll(Model model,
                         @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                         @RequestParam(defaultValue="1",value="pageSize")Integer pageSize)
    {
        if(pageNum==null || pageNum<=0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        try {
            List<Staff> all = staffMapper.getAll();
            PageInfo<Staff> pageInfo = new PageInfo<>(all, pageSize);
            model.addAttribute("pageInfo",pageInfo);
            model.addAttribute("msg",all);
        }catch (Exception e){
            model.addAttribute("msg","查询出现异常");
            return null;
        }finally {
            PageHelper.clearPage();
        }

        return null;
    }

    /**
     * 根据ID删除
     * @param model
     * @param sId
     * @return
     */
    @RequestMapping("/StaDeleteByID")
    public String StaDeleteByID(Model model,int sId){
        try {
            int deleteByID = staffMapper.StaDeleteByID(sId);
            if (deleteByID>0){
                model.addAttribute("msg","删除成功");
                return null;
            }else {
                model.addAttribute("msg","删除失败");
                return null;
            }
        }catch (Exception e){
            model.addAttribute("msg","删除出现异常");
            return null;
        }
    }

    /**
     * 根据ID查询
     * @param model
     * @param sId
     * @return
     */
    @RequestMapping("/StaGetByID")
    public String getByID(Model model,int sId){
        try {
            Staff all = staffMapper.getByID(sId);
            model.addAttribute("msg",all);
            return null;
        }catch (Exception e){
            model.addAttribute("msg","查询出现异常");
            return null;
        }
    }

    /**
     * 根据模糊名字查询
     * @param model
     * @param sCname
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getByCName")
    public String getByCName(Model model,String sCname,
                             @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                             @RequestParam(defaultValue="1",value="pageSize")Integer pageSize ) {

        System.out.println(sCname);
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 1;
        }
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<String> byCName = staffMapper.getByCName(sCname);
            PageInfo<String> info = new PageInfo<>(byCName, pageSize);
            model.addAttribute("pageInfo", info);
            model.addAttribute("msg", byCName);
            model.addAttribute("sCname",sCname);
            return null;
        } catch (Exception e){
            model.addAttribute("msg","查询出现异常");
            return null;
        }finally {
            PageHelper.clearPage();
        }

    }

    /**
     * 修改
     * @param staff
     * @param model
     * @return
     */
    @RequestMapping("/StaffUpdate")
    public String StaUpdateByID(Staff staff,Model model){
        try {
            if (staff.getSPass().equals("是")&&staff.getSJobId().equals("")){
                String JobNumber="GH";
                String JobNumbers =null;
                boolean flag=true;
                while (flag){
                    JobNumbers = JobNumber+String.valueOf((int)(Math.random()*9000+1000));
                    System.out.println(JobNumbers);
                    List<Staff> byJobID = staffMapper.getByJobID(JobNumbers);
                    if (byJobID.size()<1){
                        staff.setSJobId(JobNumbers);
                        staffMapper.StaUpdateByID(staff);
                        flag=false;
                    }
                }
            } else if (staff.getSPass().equals("否")&&!staff.getSJobId().equals("")) {
                staff.setSJobId(null);
                staffMapper.StaUpdateByID(staff);
            }else {
                staffMapper.StaUpdateByID(staff);
            }
            return null;
        }catch (Exception e){
            model.addAttribute("msg","修改出现异常");
            return null;
        }
    }

    /**
     * 添加
     * @param staff
     * @param model
     * @return
     */
    @RequestMapping("/StaAdd")
    public String StaAddByID(Staff staff,Model model){
        try {
            int staAddByID = staffMapper.StaAddByID(staff);
            if (staAddByID>0) {
                model.addAttribute("msg","添加成功");
                return null;
            }else {
                model.addAttribute("msg","添加失败");
                return null;
            }
        }catch (Exception e){
            model.addAttribute("msg","添加出现异常");
            return null;
        }
    }

}
