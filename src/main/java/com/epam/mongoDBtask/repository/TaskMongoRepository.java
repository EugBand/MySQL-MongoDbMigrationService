package com.epam.mongoDBtask.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epam.mongoDBtask.model.document.TaskDocument;

public interface TaskMongoRepository extends MongoRepository<TaskDocument, Long>, TaskRepository {

}
