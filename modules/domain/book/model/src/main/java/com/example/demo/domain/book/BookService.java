package com.example.demo.domain.book;


import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.Book;

import java.util.Collection;


public interface BookService {

    Author createAuthor(Author author);

    Book createBook(Book book);

    Collection<Author> searchAuthors(String name);
}
