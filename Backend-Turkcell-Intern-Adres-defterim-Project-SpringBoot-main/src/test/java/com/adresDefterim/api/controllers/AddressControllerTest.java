package com.adresDefterim.api.controllers;

import com.adresDefterim.business.concrates.AddressManager;
import com.adresDefterim.business.request.address.CreateAddressRequest;
import com.adresDefterim.business.request.address.DeleteAddressRequest;
import com.adresDefterim.business.request.address.UpdateAddressRequest;
import com.adresDefterim.core.result.Result;
import com.adresDefterim.core.result.SuccessResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest
@ContextConfiguration(classes = AddressController.class)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressManager addressManager;

    @Test
    public void createAddress() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(generateCreateAddressRequest());

        Result result = new SuccessResult("Adres Başarıyla Kaydedldi..");

        RequestBuilder builder = MockMvcRequestBuilders.post("/api/Address/add")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);

        Mockito.when(addressManager.add(generateCreateAddressRequest())).thenReturn(result);
        MvcResult result1 = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
        Result result2 = objectMapper.readValue(result1.getResponse().getContentAsString(), Result.class);

        assertThat(result2).hasFieldOrPropertyWithValue("success", true);

    }

    @Test
    public void updateAddress() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(generateUpdateAddressRequest());

        Result result = new SuccessResult("Adres Başarıyla Güncellendi..");

        RequestBuilder builder = MockMvcRequestBuilders.put("/api/Address/update")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);

        Mockito.when(addressManager.update(generateUpdateAddressRequest())).thenReturn(result);
        MvcResult result1 = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
        Result result2 = objectMapper.readValue(result1.getResponse().getContentAsString(), Result.class);

        assertThat(result2).hasFieldOrPropertyWithValue("success", true);

    }

    @Test
    public void deleteAddress() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(generateDeleteAddressRequest());

        Result result = new SuccessResult("Adres Başarıyla Silindi..");

        RequestBuilder builder = MockMvcRequestBuilders.delete("/api/Address/delete")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);

        Mockito.when(addressManager.delete(generateDeleteAddressRequest())).thenReturn(result);
        MvcResult result1 = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
        Result result2 = objectMapper.readValue(result1.getResponse().getContentAsString(), Result.class);

        assertThat(result2).hasFieldOrPropertyWithValue("success", true);

    }

    private CreateAddressRequest generateCreateAddressRequest() {
        return CreateAddressRequest.builder()
                .firstName("ahmet")
                .lastName("Karataş")
                .phoneNumber("05385124467")
                .addressTitle("deneme")
                .city("İstanbul")
                .country("Başakşehir")
                .state("şahintepe")
                .fullAddress("başakşehir/istanbul")
                .userId(23L)
                .build();

    }

    private UpdateAddressRequest generateUpdateAddressRequest() {
        return UpdateAddressRequest.builder()
                .addressId(15L)
                .firstName("ahmet")
                .lastName("Karataş")
                .phoneNumber("05385124467")
                .addressTitle("deneme")
                .city("İstanbul")
                .country("Başakşehir")
                .state("şahintepe")
                .fullAddress("başakşehir/istanbul")
                .userId(25L)
                .build();
    }

    private DeleteAddressRequest generateDeleteAddressRequest() {
        return DeleteAddressRequest.builder()
                .addressId(15L)
                .build();
    }


}