package com.epam.mongoDBtask.controller;

import com.epam.mongoDBtask.dto.EmployeeRequestDto;
import com.epam.mongoDBtask.dto.EmployeeResponseDto;
import com.epam.mongoDBtask.util.DbType;
import com.epam.mongoDBtask.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    private ResponseEntity<EmployeeResponseDto> getById(@PathVariable String id) {
        return employeeService.getById(id)
                .map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> getAll(@RequestParam DbType dbType) {
        List<EmployeeResponseDto> employees = employeeService.getAll(dbType);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity<Void> save(@RequestBody EmployeeRequestDto employee) {
        employeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    private ResponseEntity<Void> edit(@PathVariable String id, @RequestBody EmployeeRequestDto requestDto){
        if (employeeService.edit(id, requestDto)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
