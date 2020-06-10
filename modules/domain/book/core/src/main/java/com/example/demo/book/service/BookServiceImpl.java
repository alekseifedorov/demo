package com.example.demo.book.service;

import com.example.demo.api.paging.Page;
import com.example.demo.book.configuration.BookServiceDatasourceConfig;
import com.example.demo.book.entity.AuthorEntity;
import com.example.demo.book.entity.BookEntity;
import com.example.demo.book.mapping.AuthorMapper;
import com.example.demo.book.mapping.BookMapper;
import com.example.demo.book.repository.AuthorRepository;
import com.example.demo.book.repository.BookRepository;
import com.example.demo.book.validator.AuthorValidator;
import com.example.demo.common.validation.AnnotationValidator;
import com.example.demo.domain.book.BookService;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.AuthorSearchRequest;
import com.example.demo.domain.book.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(transactionManager = BookServiceDatasourceConfig.TRANSACTION_MANAGER_NAME)
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;
    private final AnnotationValidator<Object> annotationValidator;
    private final AuthorValidator authorValidator;

    @Override
    public Author createAuthor(Author author) {
        annotationValidator.validate(author);
        authorValidator.validate(author);

        AuthorEntity result = authorRepository.save(authorMapper.toEntity(author));
        return authorMapper.fromEntity(result);
    }

    @Override
    public Book createBook(Book book) {
        BookEntity result = bookRepository.save(bookMapper.toEntity(book));
        return bookMapper.fromEntity(result);
    }

    @Override
    public Page<Author> searchAuthors(AuthorSearchRequest request) {
        return authorRepository.searchByRequest(request);
    }
}
