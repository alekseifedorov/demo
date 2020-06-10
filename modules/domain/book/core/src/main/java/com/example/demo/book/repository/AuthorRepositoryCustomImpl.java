package com.example.demo.book.repository;

import com.example.demo.api.paging.Page;
import com.example.demo.api.paging.PageRequest;
import com.example.demo.api.paging.Sorting;
import com.example.demo.book.configuration.BookServiceDatasourceConfig;
import com.example.demo.book.entity.AuthorEntity;
import com.example.demo.book.mapping.AuthorMapper;
import com.example.demo.book.mapping.BookMapper;
import com.example.demo.domain.book.model.Author;
import com.example.demo.domain.book.model.AuthorSearchRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.awt.image.SampleModel;
import java.util.*;

@Transactional(transactionManager = BookServiceDatasourceConfig.TRANSACTION_MANAGER_NAME)
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {

    @PersistenceContext(unitName = "bookPersistenceUnit")
    private EntityManager entityManager;

    @Autowired
    private AuthorMapper mapper;

    @Override
    public Page<Author> searchByRequest(AuthorSearchRequest request) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuthorEntity> cq = cb.createQuery(AuthorEntity.class);

        Root<AuthorEntity> author = cq.from(AuthorEntity.class);


        author.fetch("books", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        if (Strings.isNotBlank(request.getName())) {
            predicates.add(cb.like(author.get("name"), request.getName() + '%'));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        PageRequest pageRequest = request.getPageRequest();
        pageRequest.getSort().forEach(
                sort -> cq.orderBy(sort.isAscending() ? cb.asc(author.get(sort.getProperty())) : cb.desc(author.get(sort.getProperty())))
        );
        TypedQuery<AuthorEntity> query = entityManager.createQuery(cq);

        query.setFirstResult(pageRequest.getOffset());
        query.setMaxResults(pageRequest.getSize());

        List<AuthorEntity> entityList = query.getResultList();

        List<Author> content = mapper.fromEntities(entityList);

        return Page.<Author>builder().content(content).page(pageRequest.getPage()).size(content.size()).build();
    }
}