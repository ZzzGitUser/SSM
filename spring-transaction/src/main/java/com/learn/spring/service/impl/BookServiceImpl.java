package com.learn.spring.service.impl;

import com.learn.spring.dao.BookDao;
import com.learn.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    /*@Transactional(
            //readOnly = true
            //timeout = 3
            //noRollbackFor = {ArithmeticException.class}
            //isolation = Isolation.DEFAULT   //使用数据库默认的隔离级别
            propagation = Propagation.REQUIRES_NEW
    )*/
    public void buyBook(Integer userId, Integer bookId) {
        /*
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        //查询图书的价格
        Integer price = bookDao.getPriceByBookId(bookId);
        //更新图书的库存
        bookDao.updateStock(bookId);
        //更新用户的余额
        bookDao.updateBalance(userId, price);

        //System.out.println(1 / 0);
    }
}
