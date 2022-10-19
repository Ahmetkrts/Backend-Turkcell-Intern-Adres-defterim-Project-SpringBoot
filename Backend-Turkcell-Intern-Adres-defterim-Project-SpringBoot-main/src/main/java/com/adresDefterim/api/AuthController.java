package com.adresDefterim.api;

import com.adresDefterim.business.abstracts.UserService;
import com.adresDefterim.business.dto.user.UserGetDto;
import com.adresDefterim.business.request.user.CreateUserRequest;
import com.adresDefterim.core.auth.TokenManager;
import com.adresDefterim.core.auth.UserDetailsService;
import com.adresDefterim.core.exception.BusinessException;
import com.adresDefterim.core.result.DataResult;
import com.adresDefterim.core.result.Result;
import com.adresDefterim.core.result.SuccessDataResult;
import com.adresDefterim.core.result.SuccessResult;
import com.adresDefterim.business.request.login.LoginRequest;
import com.adresDefterim.entities.concrates.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
//Backend-Turkcell-Intern-Adres-defterim-Project-SpringBoot
@RestController
@RequestMapping("/api/User/Auth")
public class AuthController {

    private final TokenManager tokenManager;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    public AuthController(TokenManager tokenManager, UserService userService, AuthenticationManager authenticationManager) {
        this.tokenManager = tokenManager;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public DataResult login(@RequestBody @Valid LoginRequest loginRequest) throws BusinessException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            UserGetDto request = userService.getByUserName(loginRequest.getUsername()).getData();
            Map<String, Object> response = new HashMap<>();
            response.put("token", tokenManager.generateToken(loginRequest.getUsername()));
            response.put("user", request);
            return new SuccessDataResult(response, "Kullanıcı Girişi Başarılı..");
        } catch (Exception e) {
            throw new BusinessException("Kullanıcı Adı veya Şifre Hatalıdır");
        }
    }

    @PostMapping("/refreshToken")
    public DataResult refreshToken(@RequestParam String username) throws BusinessException {
        UserGetDto request = userService.getByUserName(username).getData();
        Map<String, Object> response = new HashMap<>();
        response.put("token", tokenManager.generateToken(username));
        response.put("user", request);
        return new SuccessDataResult(response, "Token Başarıyla Oluşturuldu..");

    }


    @PostMapping("/register")
    Result add(@RequestBody @Valid CreateUserRequest createUserRequest) throws BusinessException {
        return this.userService.add(createUserRequest);
    }

}
