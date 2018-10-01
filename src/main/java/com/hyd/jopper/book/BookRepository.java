package com.hyd.jopper.book;

import com.hyd.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yiding.he
 */
@Repository
public class BookRepository {

    @Autowired
    private DAO dao;

    public List<Book> findAll() {
        return dao.query(Book.class, "select * from books");
    }
}
