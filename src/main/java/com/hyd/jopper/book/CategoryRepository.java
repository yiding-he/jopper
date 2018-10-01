package com.hyd.jopper.book;

import com.hyd.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yiding.he
 */
@Repository
public class CategoryRepository {

    @Autowired
    private DAO dao;

    public List<Category> findAll() {
        return dao.query(Category.class, "select * from categories");
    }
}
