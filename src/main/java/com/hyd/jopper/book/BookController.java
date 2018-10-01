package com.hyd.jopper.book;

import com.hyd.jopper.meta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .addOperation("编辑", "edit")
                .addOperation("删除", "delete")
                .enableForm()
                .addFormField(TextFormField.create("书名", "title"))
                .addFormField(ListFormField.<Category>create(
                        "类型", "category", categoryRepository.findAll()
                ))
                .setAutoQuery(true);
    }

    @GetMapping("/list")
    public ResourceList<Book> listBooks() {
        List<Book> list = bookRepository.findAll();
        return new ResourceList<Book>().setList(list);
    }
}
