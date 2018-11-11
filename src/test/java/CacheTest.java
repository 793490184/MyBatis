
import lizi.mybatis.bean.Employee;
import lizi.mybatis.dao.EmployeeMapper;
import lizi.mybatis.dao.EmployeeMapperAnnotation;
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

public class CacheTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(reader);
        return sqlSessionFactory;
    }

    @Test
    public void testFirstLevelCache() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee emp01 = mapper.getEmpById(1);
            System.out.println(emp01);

            System.out.println("===========");
            Employee emp02 = mapper.getEmpById(1);
            System.out.println(emp02);

            System.out.println("emp1==emp2 ? " + (emp01 == emp02));
        } finally {
            session.close();
        }
    }

    @Test
    public void testFirstLevelCache2() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session1 = sqlSessionFactory.openSession();
        SqlSession session2 = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper1 = session1.getMapper(EmployeeMapper.class);
            Employee emp01 = mapper1.getEmpById(1);
            System.out.println(emp01);

            System.out.println("===========");
            EmployeeMapper mapper2 = session2.getMapper(EmployeeMapper.class);
            Employee emp02 = mapper2.getEmpById(1);
            System.out.println(emp02);

            System.out.println("emp1==emp2 ? " + (emp01 == emp02));
        } finally {
            session1.close();
            session2.close();
        }
    }

    @Test
    public void testFirstLevelCache3() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            Employee emp01 = mapper.getEmpById(1);
            System.out.println(emp01);

            //mapper.addEmp(new Employee(null,"testCache","cache","1"));
            System.out.println("addOK");
            System.out.println("===========");
            Employee emp02 = mapper.getEmpById(1);
            System.out.println(emp02);

            System.out.println("emp1==emp2 ? " + (emp01 == emp02));
        } finally {
            session.close();
        }
    }


    @Test
    public void testSecondLevelCache() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session1 = sqlSessionFactory.openSession();
        SqlSession session2 = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper1 = session1.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = session2.getMapper(EmployeeMapper.class);

            Employee emp01 = mapper1.getEmpById(1);
            System.out.println(emp01);
            session1.close();

            System.out.println("===========");
            Employee emp02 = mapper2.getEmpById(1);
            System.out.println(emp02);
            session2.close();

            System.out.println("emp1==emp2 ? " + (emp01 == emp02));
        } finally {

        }
    }


}
