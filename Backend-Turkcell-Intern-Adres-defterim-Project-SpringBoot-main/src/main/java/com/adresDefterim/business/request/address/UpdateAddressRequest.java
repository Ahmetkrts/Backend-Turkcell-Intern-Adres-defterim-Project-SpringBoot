package com.adresDefterim.business.request.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateAddressRequest {


    @NotNull(message = "Adres İd Boş Bırakılamz!")
    private Long addressId;
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

}
