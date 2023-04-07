package com.epam.mongoDBtask.mapper;

import com.epam.mongoDBtask.dto.TaskRequestDto;
import com.epam.mongoDBtask.dto.TaskResponseDto;
import com.epam.mongoDBtask.model.entity.EmployeeEntity;
import com.epam.mongoDBtask.model.entity.TaskEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface TaskEntityMapper {

    @Mapping(target = "employeeEntity", expression = "java(employeeEntity)")
    @Mapping(target = "id", ignore = true)
    TaskEntity toTask(TaskRequestDto responseDto, EmployeeEntity employeeEntity);

    @Mapping(target = "employeeId", source = "taskEntity.employeeEntity.id")
    TaskResponseDto toTaskResponseDto(TaskEntity taskEntity);

}
