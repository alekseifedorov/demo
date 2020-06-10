package com.example.demo.api.paging;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Page with data")
public class Page<T> {

    @ApiModelProperty("Page content")
    private List<T> content = Collections.emptyList();

    @ApiModelProperty("Page number")
    private int page;

    @ApiModelProperty("Page size")
    private int size;

    public List<T> getContent() {
        return nonNull(content) ? content : Collections.emptyList();
    }
}
