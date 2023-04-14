package com.learn.service;

import com.github.pagehelper.PageInfo;
import com.learn.pojo.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * 查询所有的员工信息
     *
     * @return
     */
    List<Employee> getAllEmployee();

    /**
     * 获取员工的分页信息
     *
     * @param pageNum
     * @return
     */
    PageInfo<Employee> getEmployeePage(Integer pageNum);

    /**
     * 根据empId删除员工信息
     *
     * @param empId
     */
    void deleteEmployeeById(Integer empId);

    /**
     * 添加员工信息
     *
     * @param employee
     */
    void addEmployee(Employee employee);

    /**
     * 根据empId获取员工信息
     *
     * @param empId
     * @return
     */
    Employee getEmployeeById(Integer empId);

    void updateEmployeeById(Employee employee);
}
