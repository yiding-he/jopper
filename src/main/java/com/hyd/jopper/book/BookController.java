package com.hyd.jopper.book;

import com.hyd.jopper.meta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/resources/book")
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/meta")
    public RenderOptions bookMeta() {
        return new TableRenderOptions()
                .addColumn("书名", "mainTitle")
                .addColumn("副标题", "secondTitle")
                .addColumn("作者", "author")
                .addColumn("类型", "category")
                .addOperation("编辑", "edit")
                .addOperation("删除", "delete")
                .enableForm(form -> {
                    form.setOperation("查询", "query");
                    form.addField(TextFormField.create("书名", "title"));
                    form.addField(TextFormField.create("作者", "author"));
                    form.addField(ListFormField.create(
                            "类型", "category", categoryRepository.findAll()
                    ).addOption(0, Category.ALL));
                })
                .setAutoQuery(true);
    }

    @GetMapping("/query")
    public ResourceList<Book> queryBooks(HttpServletRequest request) {

        String titleKeyword = request.getParameter("title");
        String category = request.getParameter("category");

        List<Book> list = bookRepository.findAll(titleKeyword, category);
        list.forEach(book -> {
            String categoryName = categoryRepository.findOne(book.getCategory()).getName();
            book.setCategory(categoryName);
        });
        return new ResourceList<Book>().setList(list);
    }
}
