package com.example.demo.domain.book.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
public class Author {

    private String id;
    private String name;
    private Collection<Book> books;
}
