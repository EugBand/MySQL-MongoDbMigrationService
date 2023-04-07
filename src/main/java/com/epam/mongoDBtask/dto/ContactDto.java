package com.epam.mongoDBtask.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include. NON_NULL)
public class ContactDto {

    String contactType;

    String contact;

    String description;

}
