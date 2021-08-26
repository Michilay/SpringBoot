package com.michilay.contoller;


import com.michilay.dao.DepartmentDao;
import com.michilay.dao.EmployeeDao;
import com.michilay.pojo.Department;
import com.michilay.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    //
    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps",all);
        return "emp/list";
    }
    @Autowired
    private DepartmentDao departmentDao;

//    跳转到添加页面并且查出所有部门信息
    @GetMapping("/emp")
    public String toAdd(Model model){
        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String add(Employee employee){
//        添加
        employeeDao.save(employee);
        return "redirect:/emps";
    }

//    go修改
//    跳转到添加页面并且查出所有部门信息
    @GetMapping("/emp/{id}")
    public String toUpdate(@PathVariable("id")Integer id, Model model){
//        查出原来的数据
        Employee employeeById = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employeeById);
        Collection<Department> departments = departmentDao.getDepartment();
        model.addAttribute("departments",departments);
        return "emp/update";
    }
//      修改
    @PostMapping("/updateEmp")
    public String update(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    @GetMapping("/delemp/{id}")
    public String update(@PathVariable("id")int id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
