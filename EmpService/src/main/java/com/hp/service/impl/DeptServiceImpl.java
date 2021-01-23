package com.hp.service.impl;

import com.hp.mapper.DeptMapper;
import com.hp.pojo.Dept;
import com.hp.service.DeptService;
import com.hp.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("deptService")
@Transactional
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    public JsonData findDept() {
        List<Dept> dept = deptMapper.findDept();
        return JsonData.buildSuccess(dept,"success");
    }


    public JsonData saveDept(Dept dept) {
        int i=0;
        if (dept.getDeptId()!=null){
            deptMapper.editDept(dept);
        }else {
            deptMapper.addDept(dept);
        }
        if (i>0){
           return JsonData.buildSuccess("success");
        }else {
            return JsonData.buildError("Error");
        }
    }


    public JsonData delDept(int deptId) {
        int i = deptMapper.delDept(deptId);
        if (i>0){
            return JsonData.buildSuccess("success");
        }else {
            return JsonData.buildError("Error");
        }
    }
}
