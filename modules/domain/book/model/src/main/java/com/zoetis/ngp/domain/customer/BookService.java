package com.zoetis.ngp.domain.customer;


import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.Book;


public interface BookService {

    Author createAuthor(Author author);

    Book createBook(Book book);
}
