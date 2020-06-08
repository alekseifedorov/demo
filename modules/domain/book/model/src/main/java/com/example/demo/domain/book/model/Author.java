package com.example.demo.domain.book.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
public class Author {

    private String id;

    @Size(max = 5, message = "Name max length 5")
    @NotBlank(message = "Name must not be blank")
    private String name;

    private Collection<Book> books;
}
