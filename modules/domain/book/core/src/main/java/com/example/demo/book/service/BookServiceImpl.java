package com.example.demo.book.service;

import com.example.demo.book.entity.AuthorEntity;
import com.example.demo.book.entity.BookEntity;
import com.example.demo.book.mapping.BookMapper;
import com.example.demo.book.repository.AuthorRepository;
import com.example.demo.book.repository.BookRepository;
import com.example.demo.common.validation.AnnotationValidator;
import com.example.demo.domain.book.BookService;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookMapper mapper;
    private final AnnotationValidator<Object> annotationValidator;

    @Override
    public Author createAuthor(Author author) {
        annotationValidator.validate(author);
        AuthorEntity result = authorRepository.save(mapper.toEntity(author));
        return mapper.fromEntity(result);
    }

    @Override
    public Book createBook(Book book) {
        BookEntity result = bookRepository.save(mapper.toEntity(book));
        return mapper.fromEntity(result);
    }

    @Override
    public Collection<Author> searchAuthors(String name) {
        Collection<AuthorEntity> result = authorRepository.findByName(name);
        return mapper.fromAuthorEntities(result);
    }
}
