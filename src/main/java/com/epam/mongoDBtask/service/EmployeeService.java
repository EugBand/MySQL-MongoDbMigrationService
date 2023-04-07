package com.epam.mongoDBtask.service;

import static com.epam.mongoDBtask.util.DbType.MONGO;

import com.epam.mongoDBtask.dto.EmployeeRequestDto;
import com.epam.mongoDBtask.dto.EmployeeResponseDto;
import com.epam.mongoDBtask.mapper.EmployeeDocumentMapper;
import com.epam.mongoDBtask.mapper.EmployeeEntityMapper;
import com.epam.mongoDBtask.model.entity.ContactEntity;
import com.epam.mongoDBtask.model.entity.EmployeeEntity;
import com.epam.mongoDBtask.model.type.GenderType;
import com.epam.mongoDBtask.repository.EmployeeMongoRepository;
import com.epam.mongoDBtask.repository.EmployeeSqlRepository;
import com.epam.mongoDBtask.util.DbType;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeService {

    private final EmployeeEntityMapper entityMapper;
    private final EmployeeDocumentMapper documentMapper;

    private final EmployeeSqlRepository employeeSqlRepository;

    private final EmployeeMongoRepository employeeMongoRepository;

    public List<EmployeeResponseDto> getAll(DbType dbType) {
        return  MONGO.equals(dbType) ? employeeMongoRepository.findAll().stream().map(documentMapper::toResponseDto).toList() :
                employeeSqlRepository.findAll().stream().map(entityMapper::toResponseDto).toList();
    }

    public void save(EmployeeRequestDto requestDto) {
        EmployeeEntity employeeEntity = entityMapper.toEmployeeFromRequestDto(requestDto);
        employeeSqlRepository.save(employeeEntity);
    }

    public Optional<EmployeeResponseDto> getById(String id) {
        try {
            return Optional.of(employeeSqlRepository.getReferenceById(id))
                    .map(entityMapper::toResponseDto);
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    public boolean edit(String id, EmployeeRequestDto requestDto) {
        try {
            EmployeeEntity employeeEntity = employeeSqlRepository.getReferenceById(id);
            updateEmployee(employeeEntity, requestDto);
            employeeSqlRepository.save(employeeEntity);
            return true;
        } catch (EntityNotFoundException e) {
            return false;
        }
    }

    private void updateEmployee(EmployeeEntity employeeEntity, EmployeeRequestDto requestDto) {
        if (requestDto.getFirstName() != null) {
            employeeEntity.setFirstName(requestDto.getFirstName());
        }
        if (requestDto.getLastName() != null) {
            employeeEntity.setLastName(requestDto.getLastName());
        }
        if (requestDto.getSex() != null) {
            employeeEntity.setSex(GenderType.valueOf(requestDto.getSex()));
        }
        if (requestDto.getDob() != null) {
            employeeEntity.setDob(requestDto.getDob());
        }
        if (requestDto.getAddress() != null) {
            employeeEntity.setAddressEntity(entityMapper.toAddress(requestDto.getAddress(), employeeEntity.getId()));
        }
        if (requestDto.getContacts() != null) {
            List<ContactEntity> newContactEntities = requestDto.getContacts().stream()
                    .map(entityMapper::toContact)
                    .toList();
            employeeEntity.setContacts(newContactEntities);
        }
        if (requestDto.getPosition() != null) {
            employeeEntity.setPosition(requestDto.getPosition());
        }
        if (requestDto.getActive() != null) {
            employeeEntity.setActive(requestDto.getActive());
        }
    }
}
