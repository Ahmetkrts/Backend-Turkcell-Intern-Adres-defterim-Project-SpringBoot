package com.adresDefterim.api.controllers;

import com.adresDefterim.business.abstracts.AddressService;
import com.adresDefterim.business.dto.address.AddressGetDto;
import com.adresDefterim.business.dto.address.AddressListDto;
import com.adresDefterim.business.request.address.CreateAddressRequest;
import com.adresDefterim.business.request.address.DeleteAddressRequest;
import com.adresDefterim.business.request.address.UpdateAddressRequest;
import com.adresDefterim.core.exception.BusinessException;
import com.adresDefterim.core.result.DataResult;
import com.adresDefterim.core.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/Address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/add")
    Result add(@RequestBody @Valid CreateAddressRequest createAddressRequest) throws BusinessException {
        return this.addressService.add(createAddressRequest);
    }

    @PutMapping("/update")
    Result update(@RequestBody @Valid  UpdateAddressRequest updateAddressRequest) throws BusinessException {
        return this.addressService.update(updateAddressRequest);
    }

    @DeleteMapping("/delete")
    Result delete(@RequestBody @Valid DeleteAddressRequest deleteAddressRequest) throws BusinessException {
        return this.addressService.delete(deleteAddressRequest);
    }

    @GetMapping("/getAll")
    DataResult<List<AddressListDto>> getAll() {
        return this.addressService.getAll();
    }

    @GetMapping("/getById")
    DataResult<AddressGetDto> getById(@RequestParam Long addressId) throws BusinessException {
        return this.addressService.getById(addressId);
    }
    @GetMapping("/getByUserName")
    DataResult<List<AddressListDto>> getByUserName(@RequestParam  String userName) throws BusinessException {
        return this.addressService.getByUserName(userName);
    }
}
