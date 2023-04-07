package com.epam.mongoDBtask.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskRequestDto {

    @JsonProperty("employeeId")
    private String employeeId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private String status;

}
