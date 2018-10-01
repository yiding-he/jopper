package com.hyd.jopper.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private long id;

    private String category;

    private String mainTitle;

    private String secondTitle;

    private String author;
}
