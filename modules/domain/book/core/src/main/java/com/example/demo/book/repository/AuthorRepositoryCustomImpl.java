package com.example.demo.book.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@PersistenceContext(unitName = "bookPersistenceUnit")
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {

    @PersistenceContext(unitName = "bookPersistenceUnit")
    private EntityManager entityManager;
}