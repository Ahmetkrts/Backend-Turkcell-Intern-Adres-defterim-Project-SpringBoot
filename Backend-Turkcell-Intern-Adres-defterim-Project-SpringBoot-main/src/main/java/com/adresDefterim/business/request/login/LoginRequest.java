package com.adresDefterim.business.request.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {

    @NotNull(message = "Kullanıcı Adı Boş Bırakılamaz..")
    private String username;
    @NotNull(message = "Şifre Boş Bırakılamaz..")
    private String password;

}
