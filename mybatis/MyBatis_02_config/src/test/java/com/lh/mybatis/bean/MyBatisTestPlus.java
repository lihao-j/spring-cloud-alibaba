package com.lh.mybatis.bean;

import com.lh.mybatis.dao.EmployeeMapper;
import com.lh.mybatis.dao.EmployeeMapperDynamicSQL;
import com.lh.mybatis.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MyBatisTestPlus {

    @Test
    public void test01() throws IOException{

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try(SqlSession session = sqlSessionFactory.openSession()){
            EmployeeMapperPlus mapper = session.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        }
    }

    @Test
    public void testIf() throws IOException{

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try(SqlSession session = sqlSessionFactory.openSession()){
            EmployeeMapperDynamicSQL mapper = session.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(2, "%h%","lh01@qq.com",null);
            List<Employee> list= mapper.getEmpsByConditionIf(employee);
            for(Employee emp:list){
                System.out.println(emp);
            }

        }
    }

}
