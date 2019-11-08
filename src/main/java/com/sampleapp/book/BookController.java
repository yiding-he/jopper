package com.sampleapp.book;

import com.hyd.jopper.meta.Resp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/resources/book")
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/query")
    public Resp queryBooks(HttpServletRequest request) {

        String titleKeyword = request.getParameter("title");
        String category = request.getParameter("category");

        List<Book> bookList = bookRepository.findAll(titleKeyword, category);
        bookList.forEach(book -> {
            String categoryName = categoryRepository.findOne(book.getCategory()).getName();
            book.setCategoryName(categoryName);
        });

        return Resp.success()
            .append("list", bookList)
            .append("totalPage", 5);
    }
}
