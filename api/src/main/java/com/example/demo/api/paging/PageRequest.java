package com.example.demo.api.paging;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class PageRequest {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 100;
    private static final Direction DEFAULT_DIRECTION = Direction.ASC;

    private int page = DEFAULT_PAGE_NUMBER;

    private int size = DEFAULT_PAGE_SIZE;

    private List<Sorting> sort;

    public PageRequest(int size) {
        this(0, size);
    }

    public PageRequest(int page, int size) {
        this(page, size, null);
    }

    public PageRequest(int page, int size, List<Sorting> sort) {
        this.page = page;
        this.size = size;
        this.sort = sort != null ? sort : new ArrayList<>();
    }

    public PageRequest addSorting(String property, Direction direction) {
        return addSorting(new Sorting(property, direction));
    }

    public PageRequest addSorting(Sorting sortingDto) {
        if (isNull(sort)) {
            sort = new ArrayList<>();
        }
        sort.add(sortingDto);
        return this;
    }

    public int getOffset() {
        return page * size;
    }

    public int getPage() {
        return page < 0 ? DEFAULT_PAGE_NUMBER : page;
    }

    public int getSize() {
        return size <= 0 ? DEFAULT_PAGE_SIZE : size;
    }
}
