package com.epam.mongoDBtask.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class AddressEntity {

    @Id
    private String userId;

    @Column(length = 255, nullable = false)
    private String country;

    @Column(length = 255, nullable = false)
    private String city;

    @Column(length = 10, nullable = false)
    private String postCode;

    @Column(nullable = false)
    private String address;

}
