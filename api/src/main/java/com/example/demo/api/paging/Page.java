package com.example.demo.api.paging;

import lombok.*;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.nonNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Page<T> {

    private List<T> content = Collections.emptyList();

    private int page;

    private int size;

    public List<T> getContent() {
        return nonNull(content) ? content : Collections.emptyList();
    }
}
