package com.epam.mongoDBtask.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.epam.mongoDBtask.dto.AddressDto;
import com.epam.mongoDBtask.dto.ContactDto;
import com.epam.mongoDBtask.dto.EmployeeRequestDto;
import com.epam.mongoDBtask.dto.EmployeeResponseDto;
import com.epam.mongoDBtask.model.entity.AddressEntity;
import com.epam.mongoDBtask.model.entity.ContactEntity;
import com.epam.mongoDBtask.model.entity.EmployeeEntity;

@Mapper(componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        imports = java.util.UUID.class)
public interface EmployeeEntityMapper {

    @Mapping(target = "addressEntity", expression = "java(toAddress(requestDto.getAddress(), requestDto.getId()))")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    EmployeeEntity toEmployeeFromRequestDto(EmployeeRequestDto requestDto);

    @Mapping(target = "userId", source = "userId")
    AddressEntity toAddress(AddressDto addressDto, String userId);

    @Mapping(target = "address", source = "addressEntity")
    EmployeeResponseDto toResponseDto(EmployeeEntity employeeEntity);

    AddressDto toAddressDto(AddressEntity addressEntity);

    ContactDto toContactDto(ContactEntity contactEntity);

    @Mapping(target = "contactId", ignore = true)
    ContactEntity toContact(ContactDto contactDto);

}
