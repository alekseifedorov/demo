package com.example.demo.domain.book.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class Book {

    private String id;
    private String title;
    private Author author;
}
