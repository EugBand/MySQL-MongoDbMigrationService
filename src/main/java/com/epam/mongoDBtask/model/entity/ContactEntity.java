package com.epam.mongoDBtask.model.entity;

import com.epam.mongoDBtask.model.type.ContactType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contacts")
@Getter
@Setter
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactId", nullable = false)
    private Long contactId;

    @Enumerated(EnumType.STRING)
    @Column(name = "contactType", nullable = false)
    ContactType contactType;

    @Column(name = "contact", nullable = false)
    String contact;

    @Column(name = "description")
    String description;

}
