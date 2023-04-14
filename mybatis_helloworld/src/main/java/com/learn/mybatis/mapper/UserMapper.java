package com.learn.mybatis.mapper;

import com.learn.mybatis.pojo.User;

import java.util.List;

public interface UserMapper {
    /**
     * 添加用户信息
     */
    int insertUser();

    /**
     * 修改用户信息
     */
    void updateUser();

    /**
     * 删除用户
     */
    void deleteUser();

    /**
     * 根据id查询用户
     *
     * @return
     */
    User getUserById();

    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> selectAllUser();
}
