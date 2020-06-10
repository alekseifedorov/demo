package com.example.demo.domain.book.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private String id;

    @Size(max = 5, message = "Name max length 5")
    @NotBlank(message = "Name must not be blank")
    private String name;

    private Collection<Book> books;
}
