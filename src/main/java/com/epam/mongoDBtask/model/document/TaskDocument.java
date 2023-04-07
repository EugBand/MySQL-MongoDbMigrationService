package com.epam.mongoDBtask.model.document;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.epam.mongoDBtask.model.type.TaskStatusType;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tasks")
public class TaskDocument {

    @Id
    private Long id;

    private Long employeeId;

    @TextIndexed
    private String description;

    private TaskStatusType status;

    private LocalDateTime created;

    private LocalDateTime modified;

}
