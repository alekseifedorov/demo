package com.example.demo.book.mapping;

import com.example.demo.book.entity.AuthorEntity;
import com.example.demo.book.entity.BookEntity;
import com.example.demo.common.mapping.CommonMappings;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AuthorMapper extends CommonMappings {

    @Mappings({
            @Mapping(target = "author", ignore = true)
    })
    Book fromEntity(BookEntity entity);

    AuthorEntity toEntity(Author book);

    Author fromEntity(AuthorEntity entity);

    List<Author> fromEntities(Collection<AuthorEntity> entity);
}