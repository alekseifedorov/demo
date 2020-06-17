package com.example.demo.api.composite.book;

import com.example.demo.api.composite.book.mapping.AggregatedBookMapper;
import com.example.demo.api.paging.Page;
import com.example.demo.domain.book.BookService;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.AuthorSearchRequest;
import com.example.demo.domain.book.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class BookCompositeServiceController implements BookCompositeService {

    private final BookService bookService;
    private final AggregatedBookMapper mapper;

    @Override
    public AggregatedBook addBook(AggregatedBook aggregated) {
        Book input = mapper.fromAggregated(aggregated);
        Book created = bookService.createBook(input);
        return mapper.toAggregated(created);
    }

    @Override
    public AggregatedAuthor addAuthor(AggregatedAuthor aggregated) {
        Author input = mapper.fromAggregated(aggregated);
        Author created = bookService.createAuthor(input);
        return mapper.toAggregated(created);
    }

    @Override
    public Mono<Page<AggregatedAuthor>> searchAuthors(AggregatedAuthorSearchRequest aggregated) {
        AuthorSearchRequest request = mapper.fromAggregated(aggregated);
        Page<Author> result = bookService.searchAuthors(request);
        return Mono.just(mapper.toAggregatedAuthors(result));
    }

    @Override
    public ResponseEntity<String> adminMessage() {
        return ResponseEntity.ok("Employee or Admin message for ");
    }

    @Override
    public ResponseEntity<String> employeeMessage() {
        return ResponseEntity.ok("Employee message for " );
    }

    @Override
    public ResponseEntity<String> publicMessage() {
        return ResponseEntity.ok("Public message");
    }
}
