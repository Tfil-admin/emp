package com.hp.controller;

import com.alibaba.fastjson.JSON;
import com.hp.pojo.Dept;
import com.hp.service.DeptService;
import com.hp.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @RequestMapping("/findDept")
    public String findDept(){
        JsonData dept = deptService.findDept();
        System.out.println(dept);
        return JSON.toJSONString(dept);
    }
    @RequestMapping("/saveDept")
    public String saveDept(Dept dept){
        System.out.println("save");
        JsonData jsonData = deptService.saveDept(dept);
        return JSON.toJSONString(jsonData);
    }
    @RequestMapping("/delDept")
    public String delDept(int deptId){
        JsonData jsonData = deptService.delDept(deptId);
        System.out.println("delete");
        return JSON.toJSONString(jsonData);
    }

}
