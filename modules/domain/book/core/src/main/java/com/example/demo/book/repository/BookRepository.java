package com.example.demo.book.repository;

import com.example.demo.book.configuration.BookServiceDatasourceConfig;
import com.example.demo.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional(transactionManager = BookServiceDatasourceConfig.TRANSACTION_MANAGER_NAME)
public interface BookRepository extends JpaRepository<BookEntity, UUID> {

}
