package com.example.demo.book.repository;

import com.example.demo.api.paging.Page;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.AuthorSearchRequest;

public interface AuthorRepositoryCustom {

    Page<Author> searchByRequest(AuthorSearchRequest request);
}
