package com.newspa.taskmanager.repository;

import com.newspa.taskmanager.entity.State;
import com.newspa.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "SELECT b FROM State b WHERE b.status = :status")
    Optional<State> findStateByStatus(String status);

}
