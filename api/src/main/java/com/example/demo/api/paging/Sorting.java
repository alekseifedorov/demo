package com.example.demo.api.paging;

import lombok.*;

import javax.validation.constraints.NotNull;

import static com.example.demo.api.paging.Direction.ASC;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Sorting {

    @NotNull(message = "property is required")
    private String property;

    private Direction direction;

    public boolean isAscending() {
        return ASC == direction;
    }
}
