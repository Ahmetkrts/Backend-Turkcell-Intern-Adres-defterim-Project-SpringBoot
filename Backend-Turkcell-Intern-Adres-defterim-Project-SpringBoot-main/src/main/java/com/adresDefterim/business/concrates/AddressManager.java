package com.adresDefterim.business.concrates;

import com.adresDefterim.business.abstracts.AddressService;
import com.adresDefterim.business.abstracts.UserService;
import com.adresDefterim.business.dto.address.AddressGetDto;
import com.adresDefterim.business.dto.address.AddressListDto;
import com.adresDefterim.business.request.address.CreateAddressRequest;
import com.adresDefterim.business.request.address.DeleteAddressRequest;
import com.adresDefterim.business.request.address.UpdateAddressRequest;
import com.adresDefterim.core.exception.BusinessException;
import com.adresDefterim.core.mapping.ModelMapperService;
import com.adresDefterim.core.result.DataResult;
import com.adresDefterim.core.result.Result;
import com.adresDefterim.core.result.SuccessDataResult;
import com.adresDefterim.core.result.SuccessResult;
import com.adresDefterim.dataAccess.abstracts.AddressDao;
import com.adresDefterim.entities.concrates.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressManager implements AddressService {

    private AddressDao addressDao;
    private ModelMapperService modelMapperService;

    private UserService userService;

    @Autowired
    public AddressManager(AddressDao addressDao, ModelMapperService modelMapperService, UserService userService) {
        this.addressDao = addressDao;
        this.modelMapperService = modelMapperService;
        this.userService = userService;
    }

    @Override
    public Result add(CreateAddressRequest createAddressRequest) throws BusinessException {
        this.userService.checkIfUserIdByUserId(createAddressRequest.getUserId());
        Address response = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);

        response.setCreateDate(new Date());
        response.setAddressId(0L);
        addressDao.save(response);
        return new SuccessResult("Adres Başarıyla Kaydedldi..");
    }

    @Override
    public Result update(UpdateAddressRequest updateAddressRequest) throws BusinessException {
        this.checkIfAddressByAddressI(updateAddressRequest.getAddressId());

        Address response = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
        addressDao.save(response);
        return new SuccessResult("Adres Başarıyla Güncellendi.. ");
    }

    @Override
    public Result delete(DeleteAddressRequest deleteAddressRequest) throws BusinessException {
        this.checkIfAddressByAddressI(deleteAddressRequest.getAddressId());
        addressDao.deleteById(deleteAddressRequest.getAddressId());
        return new SuccessResult("Adres Başarıyla Silindi.. ");
    }

    @Override
    public DataResult<List<AddressListDto>> getAll() {
        List<Address> request = this.addressDao.findAll();
        List<AddressListDto> response = request.stream()
                .map(address -> this.modelMapperService.forDto().map(address, AddressListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, "Adresler Listelendi");
    }

    @Override
    public DataResult<AddressGetDto> getById(Long addressId) throws BusinessException {
        this.checkIfAddressByAddressI(addressId);
        Address request = this.addressDao.getByAddressId(addressId);
        AddressGetDto response = this.modelMapperService.forDto().map(request, AddressGetDto.class);

        return new SuccessDataResult<>(response, "Adres listelendi..");
    }

    @Override
    public DataResult<List<AddressListDto>> getByUserName(String userName) throws BusinessException {

        this.userService.checkIfUserIdByUserName(userName);
        List<Address> request = this.addressDao.getByUserUserName(userName);
        List<AddressListDto> response = request.stream()
                .map(address -> this.modelMapperService.forDto().map(address, AddressListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, "Adresler listelendi..");

    }

    private void checkIfAddressByAddressI(long addressId) throws BusinessException {
        if (!addressDao.existsById(addressId)) {
            throw new BusinessException("Adress Bulunamadı lütfen doğru adres id giriniz..");
        }
    }
}
