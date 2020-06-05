package com.example.demo.api.composite.book;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class AggregatedAuthorSearchRequest {

    private String id;

    @ApiModelProperty("Name for search")
    private String name;
}
