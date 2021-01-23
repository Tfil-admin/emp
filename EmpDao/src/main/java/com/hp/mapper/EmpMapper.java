package com.hp.mapper;

import com.hp.pojo.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpMapper {
    List<Emp> findEmp();
    int addEmp(Emp emp);
    int editEmp(Emp emp);
    int delEmp(int empId);
}
