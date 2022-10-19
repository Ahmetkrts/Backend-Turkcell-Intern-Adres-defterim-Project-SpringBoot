package com.adresDefterim.entities.concrates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="addresses")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    @Id
    @Column(name = "ADDRESS_ID")
    private Long addressId;
    @Column(name = "FIST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STATE")
    private String state;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "FULL_ADDRESS")
    private String fullAddress;
    @Column(name = "ADDRESS_TITLE")
    private String addressTitle;
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getAddressId().equals(address.getAddressId()) && Objects.equals(getFirstName(), address.getFirstName()) && Objects.equals(getLastName(), address.getLastName()) && Objects.equals(getPhoneNumber(), address.getPhoneNumber()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getState(), address.getState()) && Objects.equals(getCountry(), address.getCountry()) && Objects.equals(getFullAddress(), address.getFullAddress()) && Objects.equals(getAddressTitle(), address.getAddressTitle()) && Objects.equals(getCreateDate(), address.getCreateDate()) && Objects.equals(getUser(), address.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddressId(), getFirstName(), getLastName(), getPhoneNumber(), getCity(), getState(), getCountry(), getFullAddress(), getAddressTitle(), getCreateDate(), getUser());
    }

}
