package com.example.demo.api.composite.book;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
            value = "/add-author",
            consumes = "application/json")
    Mono<AggregatedAuthor> addAuthor(@RequestBody AggregatedAuthor book);

    @ApiOperation(
            value = "Find aggregated authors")
    @PostMapping(
            value = "/search-authors",
            consumes = "application/json")
    Flux<AggregatedAuthor> searchAuthors(@RequestBody AggregatedAuthorSearchRequest request);
}