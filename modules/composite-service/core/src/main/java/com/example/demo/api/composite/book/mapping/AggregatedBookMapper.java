package com.example.demo.api.composite.book.mapping;

import com.example.demo.api.composite.book.AggregatedAuthor;
import com.example.demo.api.composite.book.AggregatedBook;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AggregatedBookMapper {

    AggregatedBook toAggregated(Book book);

    Collection<AggregatedBook> toAggregatedBooks(Collection<Book> book);

    Book fromAggregated(AggregatedBook aggregated);

    AggregatedAuthor toAggregated(Author author);

    Collection<AggregatedAuthor> toAggregatedAuthors(Collection<Author> author);

    Author fromAggregated(AggregatedAuthor aggregated);
}