package com.hp.service;

import com.hp.pojo.Dept;
import com.hp.util.JsonData;

public interface DeptService {
    JsonData  findDept();
    JsonData  saveDept(Dept dept);
    JsonData  delDept(int deptId);


}
