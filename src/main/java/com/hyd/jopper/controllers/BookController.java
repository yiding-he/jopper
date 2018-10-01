package com.hyd.jopper.controllers;

import com.hyd.jopper.Book;
import com.hyd.jopper.meta.RenderingInfo;
import com.hyd.jopper.meta.RenderingType;
import com.hyd.jopper.meta.ResourceList;
import com.hyd.jopper.meta.TableRenderingInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/resources/book")
@RestController
public class BookController {

    @GetMapping("/list")
    public ResourceList<Book> listBooks() {
        List<Book> list = Arrays.asList(
                new Book(1, "book1", "author1"),
                new Book(2, "book2", "author2"),
                new Book(3, "book3", "author3")
        );

        return new ResourceList<Book>()
                .setList(list)
                .setRenderingInfo(
                        new TableRenderingInfo()
                                .addColumn("书名", "name")
                                .addColumn("作者", "author")
                );
    }
}
