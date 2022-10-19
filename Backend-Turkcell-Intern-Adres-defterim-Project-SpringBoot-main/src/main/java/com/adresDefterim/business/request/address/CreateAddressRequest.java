package com.adresDefterim.business.request.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateAddressRequest {

    @NotNull(message = "Ad Boş Bırakılamz!")
    private String firstName;
    @NotNull(message = "Soyad Boş Bırakılamz!")
    private String lastName;
    @NotNull(message = "Telefon Numarası Boş Bırakılamz!")
    private String phoneNumber;
    @NotNull(message = "Şehir Boş Bırakılamz!")
    private String city;
    @NotNull(message = "İlçe Boş Bırakılamz!")
    private String state;
    @NotNull(message = "Mahalle Boş Bırakılamz!")
    private String country;
    @NotNull(message = "Tam Adres Boş Bırakılamz!")
    private String fullAddress;
    @NotNull(message = "Adres Başlığı Boş Bırakılamz!")
    private String addressTitle;


    @NotNull(message = "Kullanıcı İd Boş Bırakılamz!")
    private Long userId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateAddressRequest)) return false;
        CreateAddressRequest that = (CreateAddressRequest) o;
        return getFirstName().equals(that.getFirstName()) && getLastName().equals(that.getLastName()) && getPhoneNumber().equals(that.getPhoneNumber()) && getCity().equals(that.getCity()) && getState().equals(that.getState()) && getCountry().equals(that.getCountry()) && getFullAddress().equals(that.getFullAddress()) && getAddressTitle().equals(that.getAddressTitle()) && getUserId().equals(that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getPhoneNumber(), getCity(), getState(), getCountry(), getFullAddress(), getAddressTitle(), getUserId());
    }
}
