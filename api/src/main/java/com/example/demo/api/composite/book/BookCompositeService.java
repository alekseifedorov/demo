package com.example.demo.api.composite.book;

import com.example.demo.api.paging.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@Api("REST API for books.")
public interface BookCompositeService {

    @ApiOperation("Add a book")
    @PostMapping(
            value = "/add-book",
            consumes = "application/json")
    AggregatedBook addBook(@RequestBody AggregatedBook book);

    @ApiOperation(
            value = "Add an author")
    @PostMapping(
            value = "/add-author",
            consumes = "application/json")
    AggregatedAuthor addAuthor(@RequestBody AggregatedAuthor book);

    @ApiOperation(
            value = "Find aggregated authors")
    @PostMapping(
            value = "/search-authors",
            consumes = "application/json")
    Page<AggregatedAuthor> searchAuthors(@RequestBody AggregatedAuthorSearchRequest request);
}