package com.epam.mongoDBtask.repository;

import com.epam.mongoDBtask.model.entity.TaskEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskSqlRepository extends JpaRepository<TaskEntity, Long>, TaskRepository {
}
