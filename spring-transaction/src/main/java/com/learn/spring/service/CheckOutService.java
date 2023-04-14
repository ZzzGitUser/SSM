package com.learn.spring.service;

public interface CheckOutService {

    /**
     * 结账
     *
     * @param userId
     * @param bookIds
     */
    void checkout(Integer userId, Integer[] bookIds);
}
