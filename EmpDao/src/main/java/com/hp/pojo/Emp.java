package com.hp.pojo;

public class Emp {
    private Integer empId;
    private String empName;
    private Dept dept;

    public Emp() {
        super();
    }

    public Emp(String empName, Dept dept) {
        this.empName = empName;
        this.dept = dept;
    }

    public Emp(Integer empId, String empName, Dept dept) {
        this.empId = empId;
        this.empName = empName;
        this.dept = dept;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", dept=" + dept +
                '}';
    }
}
