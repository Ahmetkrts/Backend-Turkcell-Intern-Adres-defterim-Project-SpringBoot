package com.adresDefterim.business.request.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeleteUserRequest {
    @NotNull(message = "Kullanıcı İd Boş Bırakılamaz!")
    private Long userId;



}
