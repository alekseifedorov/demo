package com.example.demo.book;

import com.example.demo.api.paging.Page;
import com.example.demo.api.paging.PageRequest;
import com.example.demo.book.configuration.TestConfig;
import com.example.demo.book.entity.AuthorEntity;
import com.example.demo.book.entity.BookEntity;
import com.example.demo.book.repository.AuthorRepository;
import com.example.demo.domain.book.BookService;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.AuthorSearchRequest;
import com.example.demo.domain.book.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
public class AuthorTest {

    @Autowired
    protected BookService bookService;

    @Test
    public void test() {
        for (int i = 0; i < 20; i++) {
            Author author = bookService.createAuthor(Author.builder().name("Ale").build());
            bookService.createBook(Book.builder().author(author).title("Title1").build());
        }

        Page<Author> result = bookService.searchAuthors(AuthorSearchRequest.builder().name("Ale").pageRequest(new PageRequest(1, 2)).build());

        assertThat(result.getContent()).hasSize(2);
    }
}
