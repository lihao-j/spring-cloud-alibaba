package com.lh.mybatis.dao;

import com.lh.mybatis.bean.Employee;

import java.util.List;

public interface EmployeeMapperDynamicSQL {
    //携带了哪个字段查询条件就带上这个字段的值
    public List<Employee> getEmpsByConditionIf(Employee employee);
}
