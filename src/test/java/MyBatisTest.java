
import lizi.mybatis.bean.Employee;
import lizi.mybatis.dao.EmployeeMapper;
import lizi.mybatis.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(reader);
        return sqlSessionFactory;
    }


    @Test
    public void test1() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2 Session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 获取接口实现对象  会创建proxy
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee empleyee = employeeMapper.getEmpById(1);
            System.out.println(employeeMapper.getClass());  // proxy
            System.out.println(empleyee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test2() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2 Session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 获取接口实现对象  会创建proxy
            EmployeeMapperAnnotation employeeMapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            Employee empleyee = employeeMapper.getEmpById(2);
            System.out.println(employeeMapper.getClass());  // proxy
            System.out.println(empleyee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test3() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2 Session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 获取接口实现对象  会创建proxy
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee empleyee = employeeMapper.getEmpByIdAndLastName(1,"tom");
            //System.out.println(employeeMapper.getClass());  // proxy
            System.out.println(empleyee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test4() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2 Session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 获取接口实现对象  会创建proxy
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id",1);
            map.put("lastName","tom");
            map.put("tableName","tbl_employee");
            Employee empleyee = employeeMapper.getEmpByMap(map);
            //System.out.println(employeeMapper.getClass());  // proxy
            System.out.println(empleyee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test5() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2 Session
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 获取接口实现对象  会创建proxy
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

            List<Employee> empleyee = employeeMapper.getEmpsByLastNameLike("%j%");
            //System.out.println(employeeMapper.getClass());  // proxy
            for (Employee emp :
                    empleyee) {
                System.out.println(emp);
            }
            //System.out.println(empleyee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test6() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = employeeMapper.getEmpByIdReturnMap(2);
            System.out.println(map);
        }   finally {
            sqlSession.close();
        }
    }

    @Test
    public void test7() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<Integer, Employee> map = employeeMapper.getEmpByLastNameLikeReturnMap("%j%");
            System.out.println(map);
        }   finally {
            sqlSession.close();
        }
    }


}
