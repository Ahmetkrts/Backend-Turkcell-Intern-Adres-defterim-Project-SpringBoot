package com.adresDefterim.business.abstracts;


import com.adresDefterim.business.request.user.CreateUserRequest;
import com.adresDefterim.business.dto.user.UserGetDto;
import com.adresDefterim.business.dto.user.UserListDto;
import com.adresDefterim.business.request.user.CreateUserRequest;
import com.adresDefterim.business.request.user.DeleteUserRequest;
import com.adresDefterim.business.request.user.UpdateUserRequest;
import com.adresDefterim.core.exception.BusinessException;
import com.adresDefterim.core.result.DataResult;
import com.adresDefterim.core.result.Result;

import java.util.List;

public interface UserService {


    Result add(CreateUserRequest createUserRequest) throws BusinessException;

    Result update(UpdateUserRequest updateUserRequest) throws BusinessException;

    Result delete(DeleteUserRequest deleteUserRequest) throws BusinessException;

    DataResult<List<UserListDto>> getAll();

    DataResult<UserGetDto> getById(Long userId) throws BusinessException;

    DataResult<UserGetDto> getByUserName(String userName) throws BusinessException;

     void checkIfUserIdByUserName(String userName) throws BusinessException;

     void checkIfUserIdByUserId(Long userId) throws BusinessException;


}
