package com.adresDefterim.api.controllers;

import com.adresDefterim.business.abstracts.UserService;
import com.adresDefterim.business.dto.user.UserGetDto;
import com.adresDefterim.business.dto.user.UserListDto;
import com.adresDefterim.business.request.user.CreateUserRequest;
import com.adresDefterim.business.request.user.DeleteUserRequest;
import com.adresDefterim.business.request.user.UpdateUserRequest;
import com.adresDefterim.core.exception.BusinessException;
import com.adresDefterim.core.result.DataResult;
import com.adresDefterim.core.result.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/User")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update")
    Result update(@RequestBody @Valid UpdateUserRequest updateUserRequest) throws BusinessException {
        return this.userService.update(updateUserRequest);
    }

    @DeleteMapping("/delete")
    Result delete(@RequestBody @Valid DeleteUserRequest deleteUserRequest) throws BusinessException {
        return this.userService.delete(deleteUserRequest);
    }

    @GetMapping("/getAll")
    DataResult<List<UserListDto>> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("/getById")
    DataResult<UserGetDto> getById(@RequestParam Long userId) throws BusinessException {
        return this.userService.getById(userId);
    }
}
