package com.epam.mongoDBtask.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.epam.mongoDBtask.mapper.EmployeeDocumentMapper;
import com.epam.mongoDBtask.mapper.TaskDocumentMapper;
import com.epam.mongoDBtask.model.document.EmployeeDocument;
import com.epam.mongoDBtask.model.document.TaskDocument;
import com.epam.mongoDBtask.model.entity.EmployeeEntity;
import com.epam.mongoDBtask.model.entity.TaskEntity;
import com.epam.mongoDBtask.repository.EmployeeMongoRepository;
import com.epam.mongoDBtask.repository.EmployeeSqlRepository;
import com.epam.mongoDBtask.repository.TaskMongoRepository;
import com.epam.mongoDBtask.repository.TaskSqlRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class MigrationService {

    private final EmployeeDocumentMapper employeeDocumentMapper;

    private final TaskDocumentMapper taskDocumentMapper;

    private final EmployeeSqlRepository employeeSqlRepository;

    private final EmployeeMongoRepository employeeMongoRepository;

    private final TaskSqlRepository taskSqlRepository;

    private final TaskMongoRepository taskMongoRepository;


    public void migrate() {
        migrateEmployeesSqlToMongo();
        migrateTasksSqlToMongo();
    }

    private void migrateTasksSqlToMongo() {
        List<TaskEntity> taskEntities = taskSqlRepository.findAll();
        List<TaskDocument> taskDocuments = taskEntities.stream().map(taskDocumentMapper::toTaskDocument).toList();
        taskMongoRepository.saveAll(taskDocuments);
    }

    private void migrateEmployeesSqlToMongo() {
        List<EmployeeEntity> employeeEntities = employeeSqlRepository.findAll();
        List<EmployeeDocument> employeeDocuments = employeeEntities.stream().map(employeeDocumentMapper::toEmployeeDocument).toList();
        employeeMongoRepository.saveAll(employeeDocuments);
    }
}
