package com.epam.mongoDBtask.mapper;

import java.util.List;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.epam.mongoDBtask.dto.AddressDto;
import com.epam.mongoDBtask.dto.ContactDto;
import com.epam.mongoDBtask.dto.EmployeeRequestDto;
import com.epam.mongoDBtask.dto.EmployeeResponseDto;
import com.epam.mongoDBtask.model.document.ContactDocument;
import com.epam.mongoDBtask.model.document.EmployeeDocument;
import com.epam.mongoDBtask.model.document.AddressDocument;
import com.epam.mongoDBtask.model.entity.EmployeeEntity;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        imports = java.util.UUID.class)
public interface EmployeeDocumentMapper {

    @Mapping(target = "addressDocument", expression = "java(toAddress(requestDto.getAddress(), requestDto.getId()))")
    @Mapping(target = "contacts", source = "requestDto.contacts")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    EmployeeDocument toEmployeeFromRequestDto(EmployeeRequestDto requestDto);

    AddressDocument toAddress(AddressDto addressDto, String userId);

    @Mapping(target = "address", source = "addressDocument")
    @Mapping(target = "contacts", source = "contacts")
    EmployeeResponseDto toResponseDto(EmployeeDocument employeeDocument);

    AddressDto toAddressDto(AddressDocument addressDocument);

    ContactDto toContactDto(ContactDocument contactDocument);

    @Mapping(target = "contactId", ignore = true)
    ContactDocument toContact(ContactDto contactDto);

    @Mapping(target = "addressDocument", source = "employeeEntity.addressEntity")
    @Mapping(target = "contacts", source = "employeeEntity.contacts")
    EmployeeDocument toEmployeeDocument(EmployeeEntity employeeEntity);
}
