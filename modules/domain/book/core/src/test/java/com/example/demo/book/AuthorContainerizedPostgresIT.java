package com.example.demo.book;

import com.example.demo.api.paging.Page;
import com.example.demo.api.paging.PageRequest;
import com.example.demo.book.configuration.TestConfig;
import com.example.demo.domain.book.BookService;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.AuthorSearchRequest;
import com.example.demo.domain.book.model.Book;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class)
@ContextConfiguration(initializers = {AuthorContainerizedPostgresIT.Initializer.class})
public class AuthorContainerizedPostgresIT {

    @Autowired
    protected BookService bookService;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "com.example.demo.book.datasource.jdbc-url=" + postgreSQLContainer.getJdbcUrl(),
                    "com.example.demo.book.datasource.username=" + postgreSQLContainer.getUsername(),
                    "com.example.demo.book.datasource.password=" + postgreSQLContainer.getPassword(),
                    "com.example.demo.book.datasource.database-name" + postgreSQLContainer.getDatabaseName()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Test
    public void test() {
        for (int i = 0; i < 20; i++) {
            Author author = bookService.createAuthor(Author.builder().name("Ale").build());
            bookService.createBook(Book.builder().author(Author.builder().id(author.getId()).build()).title("Title1").build());
        }

        Page<Author> result = bookService.searchAuthors(AuthorSearchRequest.builder().name("Ale").pageRequest(new PageRequest(1, 2)).build());

        assertThat(result.getContent()).hasSize(2);
    }
}
