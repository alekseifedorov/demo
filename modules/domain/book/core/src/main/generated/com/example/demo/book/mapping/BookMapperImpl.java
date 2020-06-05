package com.example.demo.book.mapping;

import com.example.demo.book.entity.AuthorEntity;
import com.example.demo.book.entity.BookEntity;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.Book;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-06-05T20:53:09+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public BookEntity toEntity(Book book) {
        if ( book == null ) {
            return null;
        }

        BookEntity bookEntity = new BookEntity();

        bookEntity.setId( book.getId() );
        bookEntity.setTitle( book.getTitle() );
        bookEntity.setAuthor( toEntity( book.getAuthor() ) );

        return bookEntity;
    }

    @Override
    public Book fromEntity(BookEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( entity.getId() );
        book.setTitle( entity.getTitle() );
        book.setAuthor( fromEntity( entity.getAuthor() ) );

        return book;
    }

    @Override
    public AuthorEntity toEntity(Author book) {
        if ( book == null ) {
            return null;
        }

        AuthorEntity authorEntity = new AuthorEntity();

        authorEntity.setId( book.getId() );
        authorEntity.setName( book.getName() );
        authorEntity.setBooks( bookCollectionToBookEntityCollection( book.getBooks() ) );

        return authorEntity;
    }

    @Override
    public Author fromEntity(AuthorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( entity.getId() );
        author.setName( entity.getName() );
        author.setBooks( bookEntityCollectionToBookCollection( entity.getBooks() ) );

        return author;
    }

    protected Collection<BookEntity> bookCollectionToBookEntityCollection(Collection<Book> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<BookEntity> collection1 = new ArrayList<BookEntity>( collection.size() );
        for ( Book book : collection ) {
            collection1.add( toEntity( book ) );
        }

        return collection1;
    }

    protected Collection<Book> bookEntityCollectionToBookCollection(Collection<BookEntity> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<Book> collection1 = new ArrayList<Book>( collection.size() );
        for ( BookEntity bookEntity : collection ) {
            collection1.add( fromEntity( bookEntity ) );
        }

        return collection1;
    }
}
