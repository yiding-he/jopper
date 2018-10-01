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
                new Book(1, "设计模式", "可复用面向对象软件的基础",
                        "Erich Gamma / Richard Helm / Ralph Johnson / John Vlissides"),
                new Book(2, "重构", "改善既有代码的设计", "Martin Fowler"),
                new Book(3, "敏捷软件开发", "原则、模式与实践", "Robert C·Martin")
        );

        return new ResourceList<Book>()
                .setList(list)
                .setRenderingInfo(
                        new TableRenderingInfo()
                                .addColumn("书名", "mainTitle")
                                .addColumn("副标题", "secondTitle")
                                .addColumn("作者", "author")
                                .addOperation("编辑", "edit")
                                .addOperation("删除", "delete")
                );
    }
}
