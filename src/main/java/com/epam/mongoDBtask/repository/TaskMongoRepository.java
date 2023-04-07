package com.epam.mongoDBtask.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.epam.mongoDBtask.model.document.TaskDocument;

public interface TaskMongoRepository extends MongoRepository<TaskDocument, Long>, TaskRepository {

    @Aggregation(pipeline = {
            "{ '$match': { '$text': { '$search': ?0}, 'status': ?1 } }",
            "{'$sort':{'description':?2}}"
    })
    List<TaskDocument> findTasksWithParams(String search, String status, Long sort);
}
