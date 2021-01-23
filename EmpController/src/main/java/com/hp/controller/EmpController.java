package com.hp.controller;

import com.alibaba.fastjson.JSON;
import com.hp.pojo.Emp;
import com.hp.service.EmpService;
import com.hp.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private EmpService empService;
    @RequestMapping("/findEmp")
    public String findEmp(int limit,int page){
        JsonData jsonData = empService.findEmp(limit,page);
        return JSON.toJSONString(jsonData);
    }
    @RequestMapping("/saveEmp")
    public String saveEmp(Emp emp){
        JsonData jsonData = empService.saveEmp(emp);
        return JSON.toJSONString(jsonData);
    }
    @RequestMapping("/delEmp")
    public String delEmp(int empId){
        JsonData jsonData = empService.delEmp(empId);
        return JSON.toJSONString(jsonData);
    }

}

