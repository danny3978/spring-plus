package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static sources.annotationProcessor.java.main.org.example.expert.domain.todo.entity.QTodo.todo;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryQueryDSLImpl implements TodoRepositoryQueryDSL{

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {
        Todo fetched = jpaQueryFactory
                .select(todo)
                .from(todo)
                .leftJoin(todo.user).fetchJoin()
                .where(todo.id.eq(todoId))
                .fetchOne();

        return Optional.ofNullable(fetched);
    }
}
