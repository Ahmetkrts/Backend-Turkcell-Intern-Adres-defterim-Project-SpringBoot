package com.adresDefterim.business.concrates;

import com.adresDefterim.business.abstracts.UserService;
import com.adresDefterim.business.request.address.CreateAddressRequest;
import com.adresDefterim.core.exception.BusinessException;
import com.adresDefterim.core.mapping.ModelMapperManager;
import com.adresDefterim.core.mapping.ModelMapperService;
import com.adresDefterim.core.result.Result;
import com.adresDefterim.dataAccess.abstracts.AddressDao;
import com.adresDefterim.entities.concrates.Address;
import com.adresDefterim.entities.concrates.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;



@RunWith(MockitoJUnitRunner.class)
public class AddressManagerTest {

    AddressManager addressManager;


    @Mock
    private AddressDao addressDao;
    @Mock
    private ModelMapperService modelMapperService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        addressManager = new AddressManager(addressDao, modelMapperService, userService);
    }

    @Test
    public void whenCreateAddressCalledWithValidRequest_itShouldReturnMessage() throws BusinessException {
        //Data Setler Oluşturuldu Senaryoya uygun olarak
        CreateAddressRequest createAddressRequest = generateCreateAddressRequest();
        Address address = generateAddress(createAddressRequest);
        // 12 id li kullanıcı geldiginde hata fırlatmalı test başarısız olur
        Mockito.doThrow(new BusinessException("Kullanıcı Bulunamadı")).when(userService).checkIfUserIdByUserId(12L);
        Mockito.doThrow(new BusinessException("Kullanıcı Bulunamadı")).when(userService).checkIfUserIdByUserId(18L);
        Mockito.doThrow(new BusinessException("Kullanıcı Bulunamadı")).when(userService).checkIfUserIdByUserId(16L);
        Mockito.doThrow(new BusinessException("Kullanıcı Bulunamadı")).when(userService).checkIfUserIdByUserId(22L);


        //mockito ile sahte servis davranışları belirlendi.
        Mockito.when(modelMapperService.forRequest()).thenReturn(modelMapper);
        Mockito.when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(address);
        Mockito.when(addressDao.save(address)).thenReturn(address);

        //test edilecek method cagrıldı.
        Result result = addressManager.add(createAddressRequest);

        //test edilen method kontrol edildi ve çagırılan methodların kullanılıp kullanılmadıgı doğrulandı.
        Assert.assertEquals(result.isSuccess(), true);
        Mockito.verify(addressDao).save(address);
        Mockito.verify(modelMapperService).forRequest();
    }

    @Test
    public void whenCreateAddressCalledWithNullRequest_itShouldReturnMessage() throws BusinessException {
        CreateAddressRequest createAddressRequest=new CreateAddressRequest();
        Address address = generateAddress(generateCreateAddressRequest());


        Mockito.doThrow(new BusinessException("Kullanıcı Bulunamadı.")).when(userService).checkIfUserIdByUserId(null);
        Mockito.when(modelMapperService.forRequest()).thenReturn(modelMapper);
        Mockito.when(modelMapper.map(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(address);
        Mockito.when(addressDao.save(address)).thenReturn(address);

        Result result = addressManager.add(createAddressRequest);

        Assert.assertEquals(result.isSuccess(), true);
        Mockito.verify(addressDao).save(address);
    }


    private CreateAddressRequest generateCreateAddressRequest() {
        CreateAddressRequest createAddressRequest = new CreateAddressRequest();
        createAddressRequest.setAddressTitle("ev Adresi");
        createAddressRequest.setFullAddress("başakşehir/istanbul");
        createAddressRequest.setCity("istanbul");
        createAddressRequest.setCountry("başakşehir");
        createAddressRequest.setState("şahintepe");
        createAddressRequest.setFirstName("ahmet");
        createAddressRequest.setLastName("karataş");
        createAddressRequest.setPhoneNumber("05385124467");
        createAddressRequest.setUserId(16L);
        return createAddressRequest;
    }

    private User generateUser() {
        User user = new User();
        user.setUserId(23L);
        user.setCreateDate(new Date());
        user.setUserName("test");
        user.setPassword("test12345");
        user.setMail("test@test.com");
        user.setUserFistName("ahmet");
        user.setUserLastName("Karataş");
        return user;
    }

    private Address generateAddress(CreateAddressRequest createAddressRequest) {
        Address address = new Address();
        address.setAddressTitle(createAddressRequest.getAddressTitle());
        address.setFullAddress(createAddressRequest.getFullAddress());
        address.setCity(createAddressRequest.getCity());
        address.setCountry(createAddressRequest.getCountry());
        address.setFirstName(createAddressRequest.getFirstName());
        address.setLastName(createAddressRequest.getLastName());
        address.setState(createAddressRequest.getState());
        address.setPhoneNumber(createAddressRequest.getPhoneNumber());
        address.setUser(generateUser());
        return address;
    }

}