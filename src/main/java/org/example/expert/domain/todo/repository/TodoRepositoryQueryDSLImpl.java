package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryQueryDSLImpl implements TodoRepositoryQueryDSL{

    private final JPAQueryFactory jpaQueryFactory;


}
