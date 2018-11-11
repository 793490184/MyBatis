
import lizi.mybatis.bean.Department;
import lizi.mybatis.bean.Employee;
import lizi.mybatis.dao.DepartmentMapper;
import lizi.mybatis.dao.EmployeeMapper;
import lizi.mybatis.dao.EmployeeMapperDynamicSQL;
import lizi.mybatis.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CuudPlusTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(reader);
        return sqlSessionFactory;
    }


    @Test
    public void test1() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 获取接口实现对象  会创建proxy
            EmployeeMapperPlus employeeMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee empleyee = employeeMapperPlus.getEmpById(1);
            //System.out.println(empleyee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test2() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus employeeMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee empleyee = employeeMapperPlus.getEmpById(1);
            System.out.println(empleyee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test3() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus employeeMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee empleyee = employeeMapperPlus.getEmpAndDept(1);
            System.out.println(empleyee);
            System.out.println(empleyee.getDept());
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void test4() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus employeeMapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee empleyee = employeeMapperPlus.getEmpByIdStep(2);
            System.out.println(empleyee.getLastName() + empleyee.getEmail());
            //System.out.println(empleyee);
            System.out.println(empleyee.getDept());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test5() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = departmentMapper.getDeptByIdPlus(2);
            System.out.println(department);
            System.out.println(department.getEmps());

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test6() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = departmentMapper.getDeptByIdStep(2);
            System.out.println(department.getDepartName());
            System.out.println(department.getEmps());

        } finally {
            sqlSession.close();
        }
    }


    //id 不在，报错
    //1 1=1;
    //2 where 标签
    @Test
    public void testDymicSQLif() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(3, "%e%", null, null);
            List<Employee> emps = mapper.getEmpsByConditionIf(employee);
            for (Employee emp : emps) {
                System.out.println(emp.getLastName());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDymicSQLwhere() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(3, "%e%", null, null);
            List<Employee> emps = mapper.getEmpsByConditionTrim(employee);
            for (Employee emp : emps) {
                System.out.println(emp.getLastName());
            }
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testDymicSQLchoose() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(1, "%e%", null, null);
            List<Employee> emps = mapper.getEmpsByConditionChoose(employee);
            for (Employee emp : emps) {
                System.out.println(emp.getLastName());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDymicSQLupdate() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(2, "Mater", null, null);
            mapper.updateEmp(employee);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDymicSQLforEach() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps = mapper.getEmpByConditionForeach(Arrays.asList(1, 2, 3));
            for (Employee emp : emps) {
                System.out.println(emp.getLastName());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDymicSQLBatchSave() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps = new ArrayList<>();
            emps.add(new Employee(null, "jack", "jack@123.com", "1", new Department(2)));
            emps.add(new Employee(null, "ninnn", "ninnn@123.com", "0", new Department(1)));
            mapper.addEmps(emps);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testDymicSQLInnerParam() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee();
            employee.setLastName("%e%");
            List<Employee> emps = mapper.getEmpsTestInnerParameter(employee);
            //List<Employee> emps = mapper.getEmpsTestInnerParameter(null);
            for (Employee emp : emps) {
                System.out.println(emp.getLastName());
            }
        } finally {
            sqlSession.close();
        }
    }


}
