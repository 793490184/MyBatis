package lizi.mybatis.dao;

import lizi.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    @MapKey("id") //map 使用哪个属性作为map的key
    public Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName);

    public Map<String, Object> getEmpByIdReturnMap(Integer id);

    public List<Employee> getEmpsByLastNameLike(String lastName);

    public Employee getEmpByMap(Map<String, Object> map);

    public Employee getEmpByIdAndLastName(@Param("id") Integer id, @Param("lastName ") String lastName);

    public Employee getEmpById(Integer id);

    public boolean addEmp(Employee employee);

    public boolean updateEmp(Employee employee);

    public boolean deleteEmpById(Integer id);



}
