package com.example.demo.domain.book.model;

import com.example.demo.api.paging.PageRequest;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorSearchRequest {

    private String id;

    private String name;

    private PageRequest pageRequest;
}
