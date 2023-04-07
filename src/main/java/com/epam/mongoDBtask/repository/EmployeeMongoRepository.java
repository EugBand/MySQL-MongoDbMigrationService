package com.epam.mongoDBtask.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.epam.mongoDBtask.model.document.EmployeeDocument;

public interface EmployeeMongoRepository extends MongoRepository<EmployeeDocument, String>, EmployeeRepository {
}
