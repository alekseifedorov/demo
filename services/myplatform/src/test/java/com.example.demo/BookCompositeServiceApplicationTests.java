package com.example.demo;

import com.example.demo.api.composite.book.AggregatedAuthor;
import com.example.demo.api.composite.book.AggregatedBook;
import com.example.demo.domain.book.BookService;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
@AutoConfigureWebTestClient
public class BookCompositeServiceApplicationTests {

    private ObjectMapper mapper;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private BookService bookService;

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
        mapper.writer().withDefaultPrettyPrinter();
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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

        ObjectMapper mapper = new ObjectMapper();
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/add-book").contentType(APPLICATION_JSON_VALUE)
                                                                .content(mapper.writeValueAsString(book)))
                                 .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertThat(status).isEqualTo(200);
    }
}
