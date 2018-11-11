
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
public class crud {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(reader);
        return sqlSessionFactory;
    }


    @Test
    public void add() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(null, "jerry", "jerry@123.com", "1");
            mapper.addEmp(employee);
            System.out.println(employee.getId());
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    @Test
    public void update() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(4, "jerry_new", "jerry@123.com", "1");
            boolean updateEmp =  mapper.updateEmp(employee);
            System.out.println(updateEmp);
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

    @Test
    public void delete() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            mapper.deleteEmpById(3);
            openSession.commit();
        } finally {
            openSession.close();
        }
    }




}
