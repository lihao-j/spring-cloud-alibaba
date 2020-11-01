package com.lh.mybatis.dao;

import com.lh.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

public interface EmployeeMapperAnnotation {

    @Select("select * from tbl_employee where id = #{id}")
    public Employee getEmpById(int id);
}
