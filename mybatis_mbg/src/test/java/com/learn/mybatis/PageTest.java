package com.learn.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.mybatis.mapper.EmpMapper;
import com.learn.mybatis.pojo.Emp;
import com.learn.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class PageTest {

    @Test
    public void testPage() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        //查询功能之前，开启分页功能
        Page<Object> page = PageHelper.startPage(4, 5);

        List<Emp> list = mapper.selectByExample(null);

        //查询功能之后，可以获取分页相关的所有数据
        PageInfo<Emp> pageInfo = new PageInfo<>(list, 5);

        list.forEach(System.out::println);
        System.out.println(pageInfo);
    }
}
