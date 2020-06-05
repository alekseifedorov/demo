package com.example.demo.api.composite.book.mapping;

import com.example.demo.api.composite.book.AggregatedAuthor;
import com.example.demo.api.composite.book.AggregatedAuthor.AggregatedAuthorBuilder;
import com.example.demo.api.composite.book.AggregatedBook;
import com.example.demo.api.composite.book.AggregatedBook.AggregatedBookBuilder;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.Book;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-05T20:53:15+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public AggregatedBook toAggregated(Book book) {
        if ( book == null ) {
            return null;
        }

        AggregatedBookBuilder aggregatedBook = AggregatedBook.builder();

        aggregatedBook.id( book.getId() );
        aggregatedBook.title( book.getTitle() );
        aggregatedBook.author( toAggregated( book.getAuthor() ) );

        return aggregatedBook.build();
    }

    @Override
    public Book fromAggregated(AggregatedBook aggregated) {
        if ( aggregated == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( aggregated.getId() );
        book.setTitle( aggregated.getTitle() );
        book.setAuthor( fromAggregated( aggregated.getAuthor() ) );

        return book;
    }

    @Override
    public AggregatedAuthor toAggregated(Author author) {
        if ( author == null ) {
            return null;
        }

        AggregatedAuthorBuilder aggregatedAuthor = AggregatedAuthor.builder();

        aggregatedAuthor.id( author.getId() );
        aggregatedAuthor.name( author.getName() );
        aggregatedAuthor.books( bookCollectionToAggregatedBookCollection( author.getBooks() ) );

        return aggregatedAuthor.build();
    }

    @Override
    public Author fromAggregated(AggregatedAuthor aggregated) {
        if ( aggregated == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( aggregated.getId() );
        author.setName( aggregated.getName() );
        author.setBooks( aggregatedBookCollectionToBookCollection( aggregated.getBooks() ) );

        return author;
    }

    protected Collection<AggregatedBook> bookCollectionToAggregatedBookCollection(Collection<Book> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<AggregatedBook> collection1 = new ArrayList<AggregatedBook>( collection.size() );
        for ( Book book : collection ) {
            collection1.add( toAggregated( book ) );
        }

        return collection1;
    }

    protected Collection<Book> aggregatedBookCollectionToBookCollection(Collection<AggregatedBook> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<Book> collection1 = new ArrayList<Book>( collection.size() );
        for ( AggregatedBook aggregatedBook : collection ) {
            collection1.add( fromAggregated( aggregatedBook ) );
        }

        return collection1;
    }
}
