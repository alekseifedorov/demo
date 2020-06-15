package com.example.demo.api.composite.book;

import com.example.demo.api.paging.PageRequest;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedAuthorSearchRequest {

    private String id;

    private String name;

    private PageRequest pageRequest;
}
