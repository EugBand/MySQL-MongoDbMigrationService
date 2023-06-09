package com.epam.mongoDBtask.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.mongoDBtask.dto.TaskRequestDto;
import com.epam.mongoDBtask.dto.TaskResponseDto;
import com.epam.mongoDBtask.service.TaskService;
import com.epam.mongoDBtask.util.DbType;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    private ResponseEntity<List<TaskResponseDto>> getAll(@RequestParam DbType dbType) {
        List<TaskResponseDto> tasks = service.getAll(dbType);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<TaskResponseDto> getById(
            @PathVariable
            Long id) {
        return service.getById(id)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    private ResponseEntity<Void> save(
            @RequestBody
            TaskRequestDto requestDto) {
        if (service.save(requestDto)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    private ResponseEntity<List<TaskResponseDto>> getById(@RequestParam String search, @RequestParam String status, @RequestParam Long order) {
        List<TaskResponseDto> responce = service.search(search, status, order).stream().collect(Collectors.toList());
        return responce.isEmpty() ? new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(responce, HttpStatus.OK);
    }

}
