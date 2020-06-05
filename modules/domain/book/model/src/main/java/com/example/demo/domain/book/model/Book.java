package com.example.demo.domain.book.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Book {

    private String id;
    private String title;
    private Author author;
}
