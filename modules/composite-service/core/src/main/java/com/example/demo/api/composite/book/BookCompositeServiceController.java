package com.example.demo.api.composite.book;

import com.example.demo.api.composite.book.mapping.AggregatedBookMapper;
import com.example.demo.domain.book.BookService;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class BookCompositeServiceController implements BookCompositeService {

    private final BookService bookService;
    private final AggregatedBookMapper mapper;

    @Override
    public Mono<AggregatedBook> addBook(AggregatedBook aggregated) {
        Book input = mapper.fromAggregated(aggregated);
        Book created = bookService.createBook(input);
        return Mono.just(mapper.toAggregated(created));
    }

    @Override
    public Mono<AggregatedAuthor> addAuthor(AggregatedAuthor aggregated) {
        Author input = mapper.fromAggregated(aggregated);
        Author created = bookService.createAuthor(input);
        return Mono.just(mapper.toAggregated(created));
    }

    @Override
    public Flux<AggregatedAuthor> searchAuthors(AggregatedAuthorSearchRequest request) {
        Collection<Author> result = bookService.searchAuthors(request.getName());
        return Flux.fromIterable(mapper.toAggregatedAuthors(result));
    }
}
