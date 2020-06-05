package com.example.demo.api.composite.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;

@Builder
@Getter
@Setter
@ToString
public class AggregatedAuthor {

    private String id;
    private String name;
    private Collection<AggregatedBook> books;
}
