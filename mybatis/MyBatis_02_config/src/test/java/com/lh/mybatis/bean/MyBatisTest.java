package com.lh.mybatis.bean;

import com.lh.mybatis.dao.EmployeeMapper;
import com.lh.mybatis.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.omg.CORBA.UserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {

    /**
     * 1、根据xml配置文件(全局配置文件）创建一个SqlSessionFactory对象
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取sqlSession实例，能直接执行已经映射的SQL语句
        //sql的唯一标识
        //执行SQL要用参数
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Employee employee = session.selectOne("com.lh.mybatis.dao.EmployeeMapper.getEmpById", 1);
            System.out.println(employee);
        }
    }

    @Test
    public void test02() throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try(SqlSession session = sqlSessionFactory.openSession()){
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);


        }
    }

    @Test
    public void test03() throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //获取sqlSession实例，能直接执行已经映射的SQL语句
        //sql的唯一标识
        //执行SQL要用参数
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapperAnnotation mapper = session.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        }
    }

    /**
     * 测试增删改
     */
    @Test
    public void test04() throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try(SqlSession session = sqlSessionFactory.openSession()){
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(null, "lh02", "lh02@qq.com", "1");
            //测试增加
            mapper.addEmp(employee);
            System.out.println("*******************");
            System.out.println(employee);

            //测试修改
//            Employee employee = new Employee(1, "tomTest", "tom@qq.com", "0");
//            boolean updateEmp = mapper.updateEmp(employee);
//            System.out.println(updateEmp);

            //测试删除
//            mapper.deleteEmpById(4);

            //提交修改
            session.commit();
        }
    }

    @Test
    public void test05() throws IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try(SqlSession session = sqlSessionFactory.openSession()){
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpByIdAndLastName(1, "tomTest");
            System.out.println(employee);
        }
    }

    @Test
    public void test06() throws IOException{

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try(SqlSession session = sqlSessionFactory.openSession()){
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("lastName", "tomTest");
            Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);
        }
    }

    @Test
    public void test07() throws IOException{

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try(SqlSession session = sqlSessionFactory.openSession()){
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            List<Employee> list = mapper.getEmpsByLastNameLike("%h%");
            for (Employee emp : list){
                System.out.println(emp);
            }
        }
    }

    @Test
    public void test08() throws IOException{

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try(SqlSession session = sqlSessionFactory.openSession()){
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Map<String, Object> map = mapper.getEmpByIdReturnMap(2);
            System.out.println(map);

        }
    }

    @Test
    public void test09() throws IOException{

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try(SqlSession session = sqlSessionFactory.openSession()){
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Map<Integer, Employee> map = mapper.getEmpsByLastNameLikeReturnMap("%h%");
            System.out.println(map);

        }
    }



}
