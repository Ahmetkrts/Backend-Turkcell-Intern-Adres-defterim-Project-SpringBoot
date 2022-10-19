package com.adresDefterim.dataAccess.abstracts;

import com.adresDefterim.entities.concrates.Address;
import com.adresDefterim.entities.concrates.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
@RunWith(SpringRunner.class)
@DataJpaTest
class TestHelper {

    @Autowired
    private TestEntityManager testEntityManager;


    protected Address createAddress() {
        Address address = Address.builder().firstName("ahmet")
                .lastName("Karataş")
                .phoneNumber("05385124467")
                .addressTitle("deneme")
                .city("İstanbul")
                .country("Başakşehir")
                .state("şahintepe")
                .fullAddress("başakşehir/istanbul")
                .user(createUser())
                .build();
        testEntityManager.persistAndFlush(address);
        return address;
    }

    protected User createUser() {
        User user = User.builder().createDate(new Date())
                .userName("test")
                .password("123456")
                .userFistName("Ahmet")
                .userLastName("Karataş")
                .mail("test@test.com")
                .build();

        testEntityManager.persistAndFlush(user);
        return user;

    }

}