package com.adresDefterim.business.dto.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressGetDto {

    private Long addressId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String city;
    private String state;
    private String country;
    private String fullAddress;
    private String addressTitle;
    private Date createDate;

    private String userName;
    private String mail;

}
