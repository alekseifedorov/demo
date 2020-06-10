package com.example.demo.api.composite.book;

import com.example.demo.api.paging.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedAuthorSearchRequest {

    private String id;

    @ApiModelProperty("Name for search")
    private String name;

    private PageRequest pageRequest;
}
