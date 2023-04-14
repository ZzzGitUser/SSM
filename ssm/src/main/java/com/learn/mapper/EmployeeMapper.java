package com.learn.mapper;

import com.learn.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

    /**
     * 查询所有的员工信息
     *
     * @return
     */
    List<Employee> getAllEmployee();

    /**
     * 根据empId删除员工信息
     *
     * @param empId
     */
    void deleteEmployeeById(@Param("empId") Integer empId);

    /**
     * 添加员工信息
     *
     * @param employee
     */
    void addEmployee(Employee employee);

    Employee getEmployeeById(@Param(("empId")) Integer empId);

    void updateEmployeeById(Employee employee);
}
