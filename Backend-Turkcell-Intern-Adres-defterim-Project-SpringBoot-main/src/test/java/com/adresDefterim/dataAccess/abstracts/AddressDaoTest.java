package com.adresDefterim.dataAccess.abstracts;

import com.adresDefterim.entities.concrates.Address;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;



@DataJpaTest
public class AddressDaoTest extends TestHelper {

    @Autowired
    private AddressDao addressDao;

    @Test
    public void findAddressById() {
        //expected
        Address address = createAddress();

        Address result = addressDao.getByAddressId(address.getAddressId());

        assertThat(result).isEqualTo(address);

    }

}
