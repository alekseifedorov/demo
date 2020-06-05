package com.example.demo.api.composite.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class AggregatedBook {

    private String id;
    private String title;
    private AggregatedAuthor author;
}
