package com.hp.mapper;

import com.hp.pojo.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper {
    List <Dept> findDept();
    int delDept(int deptId);
    int addDept(Dept dept);
    int editDept(Dept dept);

}
