package com.learn.spring.service.impl;

import com.learn.spring.service.BookService;
import com.learn.spring.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutServiceImpl implements CheckOutService {

    @Autowired
    private BookService bookService;

    @Override
    //@Transactional
    //一次买多本书
    public void checkout(Integer userId, Integer[] bookIds) {
        for (Integer bookId : bookIds) {
            bookService.buyBook(userId, bookId);
        }
    }
}
