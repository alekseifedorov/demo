package com.example.demo.api.composite.book;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AggregatedBook {

    private String id;
    private String title;
    private AggregatedAuthor author;
}
