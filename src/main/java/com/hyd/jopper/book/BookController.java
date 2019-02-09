package com.hyd.jopper.book;

import com.hyd.jopper.meta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
                // .addColumn("副标题", "secondTitle")
                .addColumn("作者", "author")
                .addColumn("类型", "category")
                .addOperation("编辑", "edit", "fas fa-pen")
                .addOperation("删除", "delete", "fas fa-trash-alt")
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
    public ResourceList queryBooks(HttpServletRequest request) {

        String titleKeyword = request.getParameter("title");
        String category = request.getParameter("category");

        List<Book> bookList = bookRepository.findAll(titleKeyword, category);

        List<List<String>> resourceList = bookList.stream().map(book -> {
            String categoryName = categoryRepository.findOne(book.getCategory()).getName();
            List<String> row = new ArrayList<>();
            row.add(book.getMainTitle());
            // row.add(book.getSecondTitle());
            row.add(book.getAuthor());
            row.add(categoryName);
            return row;
        }).collect(Collectors.toList());

        return new ResourceList().setList(resourceList);
    }
}
