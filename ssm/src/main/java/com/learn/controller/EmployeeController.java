package com.learn.controller;

import com.github.pagehelper.PageInfo;
import com.learn.pojo.Employee;
import com.learn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 分页查询员工信息
     *
     * @param pageNum 当前页的页码
     * @param model
     * @return
     */
    @RequestMapping(value = "/employee/page/{pageNum}", method = RequestMethod.GET)
    public String getEmployeePage(@PathVariable("pageNum") Integer pageNum, Model model) {
        //获取员工的分页信息
        PageInfo<Employee> page = employeeService.getEmployeePage(pageNum);
        //将分页数据共享到请求域中
        model.addAttribute("page", page);
        //跳转页面
        return "employee_list";
    }

    /**
     * 根据empId删除员工信息
     *
     * @param empId   被删员工的empId
     * @param pageNum 当前页的页码
     * @return
     */
    @DeleteMapping(value = "/employee/{empId}/{pageNum}")
    public String deleteEmployeeById(@PathVariable("empId") Integer empId, @PathVariable("pageNum") Integer pageNum) {
        //删除员工
        employeeService.deleteEmployeeById(empId);
        //重定向到列表功能
        return "redirect:/employee/page/" + pageNum;
    }

    @PostMapping("/employee")
    public String addEmployee(Employee employee) {
        employeeService.addEmployee(employee);
        return "redirect:/employee/page/1";
    }

    @GetMapping("/employee/{empId}")
    public String toUpdate(@PathVariable("empId") Integer empId, Model model) {
        //根据empId获取员工信息
        Employee employee = employeeService.getEmployeeById(empId);
        //将员工信息共享到请求域
        model.addAttribute("employee", employee);
        //跳转到employee_update.html
        return "employee_update";
    }

    @PutMapping("/employee")
    public String updateEmployee(Employee employee) {
        //修改员工信息
        employeeService.updateEmployeeById(employee);
        //重定向到功能列表
        return "redirect:/employee/page/1";
    }
}
