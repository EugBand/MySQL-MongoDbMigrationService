package com.epam.mongoDBtask.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDocument {

    private String country;

    private String city;

    private String postCode;

    private String address;

}
