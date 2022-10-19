package com.adresDefterim.business.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserListDto {

    private Long userId;

    private String userFistName;

    private String userLastName;

    private String userName;

    private String mail;

    private Date createDate;


}
