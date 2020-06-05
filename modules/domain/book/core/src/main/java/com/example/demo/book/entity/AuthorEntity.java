package com.example.demo.book.entity;

import com.example.demo.domain.book.model.Book;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AUTHOR")
public class AuthorEntity {

    @Id @Column(columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "author")
    private Collection<BookEntity> books;
}
