package com.example.demo.book.mapping;

import com.example.demo.book.entity.AuthorEntity;
import com.example.demo.book.entity.BookEntity;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.Book;
import com.example.demo.common.mapping.CommonMappings;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BookMapper extends CommonMappings {

    BookEntity toEntity(Book book);

    Book fromEntity(BookEntity entity);

    List<Book> fromBookEntities(Collection<BookEntity> entity);

    @Mappings({
            @Mapping(target = "books", ignore = true)
    })
    Author fromEntity(AuthorEntity entity);
}