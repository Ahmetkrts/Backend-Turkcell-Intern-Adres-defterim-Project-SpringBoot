package com.adresDefterim.business.request.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserRequest {
    @NotNull(message = "Kullanıcı İd Boş Bırakılamaz!")
    private Long userId;

    @NotNull(message = "Ad Boş Bırakılamaz!")
    private String userFistName;
    @NotNull(message = "Soyad Boş Bırakılamaz!")
    private String userLastName;
    @NotNull(message = "Kullanıcı Adı Boş Bırakılamaz!")
    private String userName;
    @NotNull(message = "Mail Adresi Boş Bırakılamaz!")
    private String mail;
    @NotNull(message = "Şifre Boş Bırakılamaz!")
    private String password;




}
