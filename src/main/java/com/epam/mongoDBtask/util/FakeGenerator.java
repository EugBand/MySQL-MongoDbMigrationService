package com.epam.mongoDBtask.util;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomUtils;

import com.epam.mongoDBtask.model.entity.AddressEntity;
import com.epam.mongoDBtask.model.entity.ContactEntity;
import com.epam.mongoDBtask.model.entity.EmployeeEntity;
import com.epam.mongoDBtask.model.entity.TaskEntity;
import com.epam.mongoDBtask.model.type.ContactType;
import com.epam.mongoDBtask.model.type.GenderType;
import com.epam.mongoDBtask.model.type.TaskStatusType;
import com.github.javafaker.Faker;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FakeGenerator {

    private final Faker faker = new Faker();

    public List<EmployeeEntity> generateEmployees(int amount) {
        return Stream.generate(() -> {
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setDob(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            employeeEntity.setAddressEntity(generateAddress());
            employeeEntity.setContacts(Stream.generate(FakeGenerator::generateContact)
                    .limit(RandomUtils.nextInt(1, 5)).collect(Collectors.toList()));
            employeeEntity.setSex(GenderType.values()[RandomUtils.nextInt(0, GenderType.values().length)]);
            employeeEntity.setPosition(faker.hacker().ingverb());
            employeeEntity.setFirstName(faker.funnyName().name());
            employeeEntity.setLastName(faker.funnyName().name());
            return employeeEntity;
        }).limit(amount).collect(Collectors.toList());
    }

    public List<TaskEntity> generateTasks(int amount) {
        return Stream.generate(() -> {
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setStatus(TaskStatusType.values()[RandomUtils.nextInt(0, TaskStatusType.values().length)]);
            taskEntity.setDescription(faker.shakespeare().hamletQuote());
            return taskEntity;
        }).limit(amount).collect(Collectors.toList());
    }

    private AddressEntity generateAddress() {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPostCode(faker.address().zipCode());
        addressEntity.setCountry(faker.address().country());
        addressEntity.setCity(faker.address().city());
        addressEntity.setAddress(faker.address().fullAddress());
        return addressEntity;
    }

    private ContactEntity generateContact() {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setContact(faker.bothify("????##@gmail.com"));
        contactEntity.setContactType(ContactType.values()[RandomUtils.nextInt(0, ContactType.values().length)]);
        contactEntity.setDescription(faker.shakespeare().hamletQuote());
        return contactEntity;
    }
}
