package com.sampleapp.book;

import com.hyd.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.hyd.dao.SQL.Select;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author yiding.he
 */
@Repository
public class BookRepository {

    @Autowired
    private DAO dao;

    public List<Book> findAll(String titleKeyword, String category) {

        String fixedKeyword = "%" + titleKeyword + "%";

        Select select = Select("*")
                .From("books")
                .Where(isNotBlank(titleKeyword),
                        "(main_title like ? or second_title like ?)",
                        fixedKeyword, fixedKeyword)
                .And(isNotBlank(category), "category=?", category);

        return dao.query(Book.class, select);
    }
}
