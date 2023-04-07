package com.epam.mongoDBtask.model.document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.epam.mongoDBtask.model.type.GenderType;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employees")
public class EmployeeDocument {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private GenderType sex;

    private LocalDate dob;

    private AddressDocument addressDocument;

    private List<ContactDocument> contacts;

    private String position;

    private Boolean active;

    private LocalDateTime created;

    private LocalDateTime modified;

}
