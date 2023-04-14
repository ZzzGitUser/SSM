package com.learn.mybatis.mapper;

import com.learn.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {

    /**
     * 根据id查询员工信息
     *
     * @param empId
     * @return
     */
    Emp getEmpById(@Param("empId") Integer empId);

    /**
     * 获取员工以及对应的部门信息
     *
     * @param empId
     * @return
     */
    Emp getEmpAndDeptByEmpId(@Param("empId") Integer empId);

    /**
     * 通过分布查询，查询员工记忆所对应的部门信息，第1步
     *
     * @param empId
     * @return
     */
    Emp getEmpAndDeptByStep1(@Param("empId") Integer empId);

    /**
     * 通过分步查询，查询部门以及部门中的员工信息，第2步
     *
     * @param deptId
     * @return
     */
    List<Emp> getDeptAndEmpByStep2(@Param("deptId") Integer deptId);
}
