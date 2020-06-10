package com.example.demo.domain.book.model;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String id;
    private String title;
    private Author author;
}
