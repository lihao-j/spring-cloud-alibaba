package com.lh.mybatis.dao;

import com.lh.mybatis.bean.Employee;

public interface EmployeeMapper {
    Employee getEmpById(int id);
}
