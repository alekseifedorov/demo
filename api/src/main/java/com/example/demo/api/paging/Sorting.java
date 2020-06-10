package com.example.demo.api.paging;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

import static com.example.demo.api.paging.Direction.ASC;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@ApiModel(description = "Field sorting")
public class Sorting {

    @ApiModelProperty("Sorting field name")
    @NotNull(message = "property is required")
    private String property;

    @ApiModelProperty("Sorting directions")
    private Direction direction;

    public boolean isAscending() {
        return ASC == direction;
    }
}
