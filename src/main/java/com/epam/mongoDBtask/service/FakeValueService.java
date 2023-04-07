package com.epam.mongoDBtask.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import com.epam.mongoDBtask.model.entity.EmployeeEntity;
import com.epam.mongoDBtask.model.entity.TaskEntity;
import com.epam.mongoDBtask.repository.EmployeeSqlRepository;
import com.epam.mongoDBtask.repository.TaskSqlRepository;
import com.epam.mongoDBtask.util.FakeGenerator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FakeValueService {

    private final EmployeeSqlRepository employeeRepository;

    private final TaskSqlRepository taskRepository;

    public void generateValues(int employeesAmount, int tasksAmount) {

        List<EmployeeEntity> employeeEntities = FakeGenerator.generateEmployees(employeesAmount);
        employeeEntities.forEach(e -> {
            e.setId(UUID.randomUUID().toString());
            e.getAddressEntity().setUserId(e.getId());
        });
        List<TaskEntity> taskEntities = FakeGenerator.generateTasks(tasksAmount);
        taskEntities.forEach(t -> t.setEmployeeEntity(employeeEntities.get(RandomUtils.nextInt(0, employeeEntities.size()))));

        employeeEntities.forEach(employeeRepository::save);
        taskEntities.forEach(taskRepository::save);
    }
}
