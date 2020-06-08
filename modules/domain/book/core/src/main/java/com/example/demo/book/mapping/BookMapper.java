package com.example.demo.book.mapping;

import com.example.demo.book.entity.AuthorEntity;
import com.example.demo.book.entity.BookEntity;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.Book;
import com.example.demo.common.mapping.CommonMappings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BookMapper extends CommonMappings {

    BookEntity toEntity(Book book);

    Book fromEntity(BookEntity entity);

    Collection<Book> fromBookEntities(Collection<BookEntity> entity);

    AuthorEntity toEntity(Author book);

    @Mappings({
            @Mapping(target = "books", ignore = true)
    })
    Author fromEntity(AuthorEntity entity);


    Collection<Author> fromAuthorEntities(Collection<AuthorEntity> entity);
}