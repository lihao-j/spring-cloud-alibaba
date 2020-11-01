package com.lh.mybatis.bean;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest {

    /**
     * 1、根据xml配置文件(全局配置文件）创建一个SqlSessionFactory对象
     * @throws IOException
     */
    @Test
    public void test() throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取sqlSession实例，能直接执行已经映射的SQL语句
        //sql的唯一标识
        //执行SQL要用参数
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Employee employee = session.selectOne("com.lh.mybatis.dao.EmployeeMapper.selectEmp", 1);
            System.out.println(employee);
        }
    }

}
