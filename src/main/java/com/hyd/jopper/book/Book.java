package com.hyd.jopper.book;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private long id;

    private String category;

    private String categoryName;

    private String mainTitle;

    private String secondTitle;

    private String author;
}
