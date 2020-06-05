package com.example.demo.api.composite.book;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Api("REST API for books.")
public interface BookCompositeService {

    @ApiOperation("Add a book")
    @PostMapping(
            value = "/add-book",
            consumes = "application/json")
    Mono<AggregatedBook> addBook(@RequestBody AggregatedBook book);

    @ApiOperation(
            value = "Add an author")
    @PostMapping(
            value = "/add-book",
            consumes = "application/json")
    // for example
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request, invalid format of the request. See response message for more information."),
            @ApiResponse(code = 404, message = "Not found, the specified id does not exist."),
            @ApiResponse(code = 422, message = "Unprocessable entity, input parameters caused the processing to fail. See response message for more information.")
    })
    Mono<AggregatedAuthor> addAuthor(@RequestBody AggregatedAuthor book);

    @ApiOperation(
            value = "Find aggregated authors")
    @PostMapping(
            value = "/search-authors",
            consumes = "application/json")
    Mono<Collection<AggregatedAuthor>> searchAuthors(@RequestBody AggregatedAuthorSearchRequest request);
}