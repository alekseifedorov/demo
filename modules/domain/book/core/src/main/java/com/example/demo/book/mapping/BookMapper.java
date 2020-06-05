package com.example.demo.book.mapping;

import com.example.demo.book.entity.AuthorEntity;
import com.example.demo.book.entity.BookEntity;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BookMapper {

    BookEntity toEntity(Book book);

    Book fromEntity(BookEntity entity);

    AuthorEntity toEntity(Author book);

    Author fromEntity(AuthorEntity entity);
}