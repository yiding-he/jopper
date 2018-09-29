package com.hyd.jopper.controllers;

import com.hyd.jopper.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/resources/book")
@RestController
public class BookController {

    @GetMapping("/list")
    public List<Book> listBooks() {
        return Arrays.asList(
                new Book(1, "book1", "author1"),
                new Book(2, "book2", "author2"),
                new Book(3, "book3", "author3")
        );
    }
}
