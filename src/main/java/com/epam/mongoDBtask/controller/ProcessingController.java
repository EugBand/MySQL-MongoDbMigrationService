package com.epam.mongoDBtask.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.mongoDBtask.service.FakeValueService;
import com.epam.mongoDBtask.service.MigrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1/processes", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProcessingController {

    private final FakeValueService fakeValueService;
    private final MigrationService migrationService;

    @PostMapping
    public ResponseEntity<Void> generateFakes(
            @RequestParam
            int employees,
            @RequestParam
            int tasks) {
        fakeValueService.generateValues(employees, tasks);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> migrateToMongo() {
        migrationService.migrate();
        return ResponseEntity.ok().build();
    }

}
