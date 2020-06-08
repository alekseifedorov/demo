package com.example.demo.book.repository;

import com.example.demo.book.configuration.BookServiceDatasourceConfig;
import com.example.demo.book.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

@Repository
@Transactional(transactionManager = BookServiceDatasourceConfig.TRANSACTION_MANAGER_NAME)
public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {

    Collection<AuthorEntity> findByName(String name);
}
