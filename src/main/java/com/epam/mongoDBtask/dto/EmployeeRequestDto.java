package com.epam.mongoDBtask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class EmployeeRequestDto {

    @JsonProperty("id")
    private String id = UUID.randomUUID().toString();

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("dob")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dob;


    @JsonProperty("address")
    private AddressDto address;

    @JsonProperty("contacts")
    private List<ContactDto> contacts;

    @JsonProperty("position")
    private String position;

    @JsonProperty("active")
    private Boolean active;

}
