package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryQueryDSL {

    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")
    Page<Todo> findAll(Pageable pageable, Sort sort);

    @Query("SELECT t FROM Todo t LEFT JOIN FETCH t.user u WHERE t.weather = :weather ORDER BY t.modifiedAt DESC")
    Page<Todo> findByWeather(String weather, Pageable pageable, Sort sort);

    @Query("SELECT t FROM Todo t " +
            "left join fetch t.user u where t.modifiedAt > :startDateTime and t.modifiedAt < :endDateTime order by t.modifiedAt desc ")
    Page<Todo> findByModifiedAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable, Sort sort);

    @Query("SELECT t FROM Todo t " +
            "left join fetch t.user u where t.modifiedAt < :endDateTime order by t.modifiedAt desc ")
    Page<Todo> findByModifiedAtIsBefore(LocalDateTime endDateTime, Pageable pageable, Sort sort);

    @Query("SELECT t FROM Todo t " +
            "left join fetch t.user u where t.modifiedAt < :startDateTime order by t.modifiedAt desc ")
    Page<Todo> findByModifiedAtIsAfter(LocalDateTime startDateTime, Pageable pageable, Sort sort);

    @Query("SELECT t FROM Todo t " +
            "LEFT JOIN t.user " +
            "WHERE t.id = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);
}
