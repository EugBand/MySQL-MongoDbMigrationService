package com.epam.mongoDBtask.service;

import static com.epam.mongoDBtask.util.DbType.MONGO;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.epam.mongoDBtask.dto.TaskRequestDto;
import com.epam.mongoDBtask.dto.TaskResponseDto;
import com.epam.mongoDBtask.mapper.TaskDocumentMapper;
import com.epam.mongoDBtask.mapper.TaskEntityMapper;
import com.epam.mongoDBtask.model.entity.EmployeeEntity;
import com.epam.mongoDBtask.model.entity.TaskEntity;
import com.epam.mongoDBtask.repository.EmployeeSqlRepository;
import com.epam.mongoDBtask.repository.TaskMongoRepository;
import com.epam.mongoDBtask.repository.TaskSqlRepository;
import com.epam.mongoDBtask.util.DbType;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskSqlRepository taskSqlRepository;

    private final TaskMongoRepository taskMongoRepository;

    private final EmployeeSqlRepository employeeSqlRepository;

    private final EmployeeSqlRepository employeeMongoRepository;

    private final TaskEntityMapper entityMapper;

    private final TaskDocumentMapper documentMapper;

    public List<TaskResponseDto> getAll(DbType dbType) {
        return MONGO.equals(dbType) ? taskMongoRepository.findAll().stream().map(documentMapper::toTaskResponseDto).toList() :
                taskSqlRepository.findAll().stream().map(entityMapper::toTaskResponseDto).toList();
    }

    public boolean save(TaskRequestDto requestDto) {
        try {
            String employeeId = requestDto.getEmployeeId();
            EmployeeEntity employeeEntity = employeeSqlRepository.getReferenceById(employeeId);

            TaskEntity taskEntity = entityMapper.toTask(requestDto, employeeEntity);
            taskSqlRepository.save(taskEntity);
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    public Optional<TaskResponseDto> getById(Long id) {
        try {
            TaskEntity taskEntity = taskSqlRepository.getReferenceById(id);
            TaskResponseDto taskResponseDto = entityMapper.toTaskResponseDto(taskEntity);
            return Optional.of(taskResponseDto);
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }
}
