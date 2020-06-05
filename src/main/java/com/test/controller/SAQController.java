package com.test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.dao.*;
import com.test.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/SAQ")
public class SAQController {
    @Resource
    private SAQMapper saqMapper;
    @Resource
    private StaffMapper staffMapper;

    /**
     * 李展龙
     * 于6/4 19.56分开始修改
     */
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private PostionMapper postionMapper;
    @Resource
    private SJCMapper sjcMapper;
    @Resource
    private SLCMapper slcMapper;

    /**
     * 分页全查询
     * @param model
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getSaqAll")
    public String getSaqAll(
            Model model,
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
            List<StaffApplyQuit> all = saqMapper.getAll();
            PageInfo<StaffApplyQuit> pageInfo = new PageInfo<>(all, pageSize);
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
     * 通过名字模糊查询合同
     * @param model
     * @param CName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getSAQByLikeCname")
    public String getByCName(Model model,String CName,
                             @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                             @RequestParam(defaultValue="1",value="pageSize")Integer pageSize ) {
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 1;
        }
        PageHelper.startPage(pageNum, pageSize);
        try {
            List<String> saqByLikeCname = saqMapper.getSAQByLikeCname(CName);
            PageInfo<String> info = new PageInfo<>(saqByLikeCname, pageSize);
            model.addAttribute("pageInfo", info);
            model.addAttribute("msg", saqByLikeCname);
            model.addAttribute("sCname",CName);
            return null;
        } catch (Exception e){
            model.addAttribute("msg","查询出现异常");
            return null;
        }finally {
            PageHelper.clearPage();
        }

    }

    /**
     * 根据ID查询
     * @param model
     * @param saqId
     * @return
     */
    @RequestMapping("/getSAQById")
    public String getSAQById(Model model,int saqId){
        try {
            List<StaffApplyQuit> saqById = saqMapper.getSAQById(saqId);
            model.addAttribute("msg",saqById);
            return null;
        }catch (Exception e){
            model.addAttribute("msg","查询出现异常");
            return null;
        }
    }

    /**
     * 修改
     * @param staffApplyQuit
     * @param model
     * @return
     */
    @RequestMapping("/SAQUpdateByID")
    public String SAQUpdateByID(StaffApplyQuit staffApplyQuit, Model model){
        try {
            if (staffApplyQuit.getSaqPass().equals("是")&&staffApplyQuit.getSaqNumber().equals("")){
                String SAQNumber="LZSQ";
                String SAQNumbers =null;
                //通过后的一系列操作
                boolean flag=true;
                while (flag){
                    SAQNumbers = SAQNumber+String.valueOf((int)(Math.random()*9000+1000));
                    System.out.println(SAQNumbers);
                    List<StaffApplyQuit> saqByNumber = saqMapper.getSAQByNumber(SAQNumbers);
                    if (saqByNumber.size()<1){
                        staffApplyQuit.setSaqNumber(SAQNumbers);
                        /**
                         * 于6/4 19.56分开始修改
                         */
                        //修改通过时间
                        Date date = new Date();
                        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                        Timestamp timestamp = Timestamp.valueOf(format);
                        staffApplyQuit.setSaqLoseDate(timestamp);
                        saqMapper.SAQUpdateByID(staffApplyQuit);
                        flag=false;
                    }
                }
            } else if (staffApplyQuit.getSaqPass().equals("否")&&!staffApplyQuit.getSaqNumber().equals("")) {
                staffApplyQuit.setSaqNumber(null);
                saqMapper.SAQUpdateByID(staffApplyQuit);
            }else {
                saqMapper.SAQUpdateByID(staffApplyQuit);
            }
            return null;
        }catch (Exception e){
            model.addAttribute("msg","修改出现异常");
            return null;
        }
    }

    /**
     * 添加
     * 李展龙
     * 于6/4 19.56分开始修改
     * 尝试上传
     * @param staffApplyQuit
     * @param model
     * @param sId
     * @return
     */
    @RequestMapping("/SAQAddByID")
    public String SAQAddByID(StaffApplyQuit staffApplyQuit,Model model,int sId){
        try {
            //获取辞职名字
            Staff staff = staffMapper.getByID(sId);
            String CName= staff.getSCname();
            //获取人力资源的信息
            StaffJobControl SJCById = sjcMapper.getByStaffId(sId);
            //获取劳动合同
            StaffLaborContract SLCbyId = slcMapper.getById(SJCById.getSjcSlcId());
            //获取部门职位
            Department dep = departmentMapper.getById(SJCById.getSjcDepId());
            Postion pos = postionMapper.getById(SJCById.getSjcPosId());
            //获取表申请时间
            Date date = new Date();
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            Timestamp timestamp = Timestamp.valueOf(format);
            //相应赋值
            staffApplyQuit.setSaqApplyDate(timestamp);
            staffApplyQuit.setSaqLoseDate(SLCbyId.getSlcLoseDate());
            staffApplyQuit.setSaqNowDep(dep.getDepName());
            staffApplyQuit.setSaqNowPos(pos.getPosName());
            staffApplyQuit.setSaqEntryDate(SLCbyId.getSlcSignDate());
            staffApplyQuit.setSaqStaffClass(SJCById.getSjcClass());
            return null;
        }catch (Exception e){
            model.addAttribute("msg","添加异常");
            return null;
        }
    }

    /**
     * 删除
     * @param model
     * @param saqId
     * @return
     */
    @RequestMapping("/SAQDeleteById")
    public String SAQDeleteById(Model model,int saqId){
        try {
            int byId = saqMapper.SAQDeleteById(saqId);
            if (byId>0){
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


}
