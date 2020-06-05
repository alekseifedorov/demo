package com.example.demo.book.repository;

import com.example.demo.book.configuration.BookServiceDatasourceConfig;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Transactional(transactionManager = BookServiceDatasourceConfig.TRANSACTION_MANAGER_NAME)
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {

    @PersistenceContext(unitName = "bookPersistenceUnit")
    private EntityManager entityManager;
}