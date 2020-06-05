package com.example.demo.api.composite.book;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class BookCompositeServiceController implements BookCompositeService {

    private final BookCompositeService bookCompositeService;

    @Override
    public Mono<AggregatedBook> addBook(AggregatedBook book) {
        return bookCompositeService.addBook(book);
    }

    @Override
    public Mono<AggregatedAuthor> addAuthor(AggregatedAuthor book) {
        return null;
    }

    @Override
    public Mono<Collection<AggregatedAuthor>> searchAuthors(AggregatedAuthorSearchRequest request) {
        return null;
    }
}
