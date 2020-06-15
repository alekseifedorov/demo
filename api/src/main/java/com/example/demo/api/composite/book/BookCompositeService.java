package com.example.demo.api.composite.book;

import com.example.demo.api.paging.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Book composite service", description = "REST API for books.")
public interface BookCompositeService {

    @Operation(summary = "Add a book")
    @PostMapping(
            value = "/add-book",
            consumes = "application/json")
    AggregatedBook addBook(@RequestBody AggregatedBook book);

    @Operation(summary = "Add an author")
    @PostMapping(
            value = "/add-author",
            consumes = "application/json")
    AggregatedAuthor addAuthor(@RequestBody AggregatedAuthor book);

    @Operation(summary = "Find aggregated authors")
    @PostMapping(
            value = "/search-authors",
            consumes = "application/json")
    Page<AggregatedAuthor> searchAuthors(@RequestBody AggregatedAuthorSearchRequest request);
}