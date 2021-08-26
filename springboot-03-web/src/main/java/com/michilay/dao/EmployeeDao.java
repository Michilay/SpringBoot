package com.michilay.dao;

import com.michilay.pojo.Department;
import com.michilay.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;
    //员工有所属的部门
    @Autowired
    private DepartmentDao departmentDao;
    static {
//        创建一个部门表
        employees = new HashMap<Integer, Employee>();
        employees.put(1001,new Employee(1001,"Michilay","1912400157@qq.com",1,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"ad23323f","5425244444@qq.com",0,new Department(102,"市场部")));
        employees.put(1003,new Employee(1003,"qweshhad","7867274827@qq.com",1,new Department(103,"教研部")));
        employees.put(1004,new Employee(1004,"mclggggy","1912400157@qq.com",0,new Department(104,"办公室")));
        employees.put(1005,new Employee(1005,"12gg3434","1275257527@qq.com",1,new Department(105,"运营部")));
    }
//    主键自增
    private static Integer initID = 1006;
//    增加员工
    public void save(Employee employee){
        if(employee.getId()==null){
            employee.setId(initID++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }
//    查询全部员工
    public Collection<Employee> getAll(){
        return employees.values();
    }
//    通过id
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }
//    删除
    public void delete(Integer id){
        employees.remove(id);
    }

}
