package com.example.demo.api.composite.book;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedBook {

    private String id;
    private String title;
    private AggregatedAuthor author;
}
