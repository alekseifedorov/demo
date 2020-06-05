package com.example.demo.book;

import com.example.demo.book.configuration.TestConfig;
import com.example.demo.book.entity.AuthorEntity;
import com.example.demo.book.repository.AuthorRepository;
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
public class UserRepositoryTCLiveTest  {

    @Autowired
    protected AuthorRepository authorRepository;

    @Test
    @Transactional
    public void test() {
        AuthorEntity author = authorRepository.save(AuthorEntity.builder().name("John").build());
        Optional<AuthorEntity> received = authorRepository.findById(author.getId());
        assertThat(received.map(AuthorEntity::getName).orElse(null)).isEqualTo(author.getName());
    }
}
