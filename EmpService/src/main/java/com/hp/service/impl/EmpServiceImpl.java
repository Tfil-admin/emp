package com.hp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hp.mapper.EmpMapper;
import com.hp.pojo.Emp;
import com.hp.service.EmpService;
import com.hp.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("empService")
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    public JsonData findEmp(int limit,int page) {
        PageHelper.startPage(page,limit);
        List<Emp> emp = empMapper.findEmp();
        PageInfo<Emp> pageInfo=new PageInfo<Emp>(emp);
        return JsonData.buildSuccess(pageInfo,"success");
    }

    public JsonData saveEmp(Emp emp) {
        int i=0;
        if (emp.getEmpId()!=null){
            empMapper.editEmp(emp);
        }else {
            empMapper.addEmp(emp);
        }
        if (i>0){
           return JsonData.buildSuccess("success");
        }else {
           return JsonData.buildError("error");
        }
    }


    public JsonData delEmp(int empId) {
        int i = empMapper.delEmp(empId);
        if (i>0){
            return JsonData.buildSuccess("success");
        }else {
            return  JsonData.buildError("error");
        }
    }
}
