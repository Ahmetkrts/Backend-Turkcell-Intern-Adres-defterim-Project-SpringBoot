package com.adresDefterim.business.abstracts;


import com.adresDefterim.business.dto.address.AddressGetDto;
import com.adresDefterim.business.dto.address.AddressListDto;
import com.adresDefterim.business.request.address.CreateAddressRequest;
import com.adresDefterim.business.request.address.DeleteAddressRequest;
import com.adresDefterim.business.request.address.UpdateAddressRequest;
import com.adresDefterim.core.exception.BusinessException;
import com.adresDefterim.core.result.DataResult;
import com.adresDefterim.core.result.Result;

import java.util.List;

public interface AddressService {

    Result add(CreateAddressRequest createAddressRequest) throws BusinessException;

    Result update(UpdateAddressRequest updateAddressRequest) throws BusinessException;

    Result delete(DeleteAddressRequest deleteAddressRequest) throws BusinessException;

    DataResult<List<AddressListDto>> getAll();

    DataResult<AddressGetDto> getById(Long addressId) throws BusinessException;

    DataResult<List<AddressListDto>> getByUserName(String userName) throws BusinessException;

}
