package com.test.controller;

import com.test.entity.StaffLaborContract;
import com.test.serviceImpl.SLCServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/slc")
public class SLCController {

    @Autowired
    private SLCServiceImpl slcService;

    @RequestMapping("/all")
    public String getAll(Model model){
        List<StaffLaborContract> list = slcService.getAll();
        for (StaffLaborContract s :
                list) {
            s.setSignDate(s.getSlcSignDate());
            s.setEfficaciousDate(s.getSlcEfficaciousDate());
            s.setLoseDate(s.getSlcLoseDate());
        }
        model.addAttribute("list",list);
        return "SLCAll";
    }
}
