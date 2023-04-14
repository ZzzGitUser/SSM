package com.learn.mybatis;

import com.learn.mybatis.mapper.EmpMapper;
import com.learn.mybatis.pojo.Emp;
import com.learn.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MBGTest {

    @Test
    public void testMBG() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        /*//根据id查询数据
        Emp emp = mapper.selectByPrimaryKey(1);
        System.out.println(emp);*/

        /*//查询所有数据
        List<Emp> list = mapper.selectByExample(null);
        list.forEach(System.out::println);*/

        /*//根据条件查询数据
        EmpExample empExample = new EmpExample();
        empExample.createCriteria().andEmpNameLike("%小明%").andAgeGreaterThanOrEqualTo(20);
        empExample.or().andGenderEqualTo("女");
        List<Emp> list1 = mapper.selectByExample(empExample);
        list1.forEach(System.out::println);*/

        Emp emp = new Emp(1, "小黑", null, "女");
        /*//测试普通修改功能
        mapper.updateByPrimaryKey(emp);*/
        //测试选择性修改
        mapper.updateByPrimaryKeySelective(emp);
    }
}
