package com.epam.mongoDBtask.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.epam.mongoDBtask.dto.TaskRequestDto;
import com.epam.mongoDBtask.dto.TaskResponseDto;
import com.epam.mongoDBtask.model.document.EmployeeDocument;
import com.epam.mongoDBtask.model.document.TaskDocument;
import com.epam.mongoDBtask.model.entity.TaskEntity;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface TaskDocumentMapper {

    TaskDocument toTask(TaskRequestDto responseDto, EmployeeDocument employeeDocument);

    TaskResponseDto toTaskResponseDto(TaskDocument taskDocument);

    @Mapping(target = "employeeId", source = "taskEntity.employeeEntity.id")
    TaskDocument toTaskDocument(TaskEntity taskEntity);

}
