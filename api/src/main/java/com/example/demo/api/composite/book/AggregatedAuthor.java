package com.example.demo.api.composite.book;

import lombok.*;

import java.util.Collection;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedAuthor {

    private String id;
    private String name;
    private Collection<AggregatedBook> books;
}
