package com.hp.service;

import com.hp.pojo.Emp;
import com.hp.util.JsonData;

public interface EmpService {
    JsonData  findEmp(int limit, int page);
    JsonData  saveEmp(Emp emp);
    JsonData  delEmp(int empId);
}
