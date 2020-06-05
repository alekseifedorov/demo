package com.example.demo.book.service;

import com.example.demo.book.entity.AuthorEntity;
import com.example.demo.book.entity.BookEntity;
import com.example.demo.book.mapping.BookMapper;
import com.example.demo.book.repository.AuthorRepository;
import com.example.demo.book.repository.BookRepository;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.Book;
import com.zoetis.ngp.domain.customer.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookMapper mapper;

    @Override
    public Author createAuthor(Author author) {
        AuthorEntity result = authorRepository.save(mapper.toEntity(author));
        return mapper.fromEntity(result);
    }

    @Override
    public Book createBook(Book book) {
        BookEntity result = bookRepository.save(mapper.toEntity(book));
        return mapper.fromEntity(result);
    }
}
