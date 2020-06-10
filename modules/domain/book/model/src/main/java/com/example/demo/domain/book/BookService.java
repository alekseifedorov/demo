package com.example.demo.domain.book;


import com.example.demo.api.paging.Page;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.AuthorSearchRequest;
import com.example.demo.domain.book.model.Book;


public interface BookService {

    Author createAuthor(Author author);

    Book createBook(Book book);

    Page<Author> searchAuthors(AuthorSearchRequest request);
}
