package com.example.demo;

import com.example.demo.api.composite.book.AggregatedAuthor;
import com.example.demo.api.composite.book.AggregatedBook;
import com.example.demo.domain.book.BookService;
import com.example.demo.domain.book.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BookCompositeServiceApplicationTests {

    @Autowired
    private WebTestClient client;

    @MockBean
    private BookService bookService;

    @Before
    public void setUp() {
        when(bookService.createBook(any(Book.class))).thenAnswer(i -> i.getArguments()[0]);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void addBook() throws Exception {
        AggregatedBook book = AggregatedBook.builder()
                                            .title("Title1")
                                            .author(AggregatedAuthor.builder().id("772c2a80-40bd-4676-b199-d2fd84b60fe1")
                                                                    .build()).build();

        client.post()
              .uri("/add-book")
              .body(Mono.just(book), AggregatedBook.class)
              .accept(APPLICATION_JSON)
              .exchange()
              .expectStatus().isEqualTo(HttpStatus.OK)
              .expectHeader().contentType(APPLICATION_JSON)
              .expectBody();
    }
}
