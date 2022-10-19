package com.adresDefterim.business.request.address;

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
public class DeleteAddressRequest {

    @NotNull(message = "Adres İd Boş Bırakılamz!")
    private Long addressId;


}
