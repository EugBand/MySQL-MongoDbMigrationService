package com.epam.mongoDBtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.mongoDBtask.model.entity.EmployeeEntity;

public interface EmployeeSqlRepository extends JpaRepository<EmployeeEntity, String>, EmployeeRepository {
}
