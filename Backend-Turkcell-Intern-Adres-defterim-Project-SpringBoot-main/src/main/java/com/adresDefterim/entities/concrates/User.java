package com.adresDefterim.entities.concrates;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "USER_FIST_NAME")
    private String userFistName;
    @Column(name = "USER_LAST_NAME")
    private String userLastName;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "MAIL")
    private String mail;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @OneToMany(mappedBy = "user")
    private List<Address> addressList;


}
