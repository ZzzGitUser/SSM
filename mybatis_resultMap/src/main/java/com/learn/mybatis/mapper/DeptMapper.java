package com.learn.mybatis.mapper;

import com.learn.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {

    /**
     * 通过分步查询，查询员工以及所对应的部门信息，第2步
     *
     * @param deptId
     * @return
     */
    Dept getEmpAndDeptByStep2(@Param("deptId") Integer deptId);

    /**
     * 查询部门，以及部门中的员工信息
     *
     * @param deptId
     * @return
     */
    Dept getDeptAndEmpByDeptId(@Param("deptId") Integer deptId);

    /**
     * 通过分步查询，查询部门以及部门中的员工信息，第1步
     *
     * @param deptId
     * @return
     */
    Dept getDeptAndEmpByStep1(@Param("deptId") Integer deptId);
}
