package com.adresDefterim;

import com.adresDefterim.core.exception.BusinessException;
import com.adresDefterim.core.result.ErrorDataResult;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
@SpringBootApplication
public class SpringBootApplicationAdresDefterim {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationAdresDefterim.class, args);
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }


    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationErrors(MethodArgumentNotValidException argumentNotValidException) {
        Map<String, String> handleValidation = new HashMap<String, String>();

        for (FieldError fieldError : argumentNotValidException.getBindingResult().getFieldErrors()) {
            handleValidation.put(fieldError.getField(), fieldError.getCode());
        }
        return new ErrorDataResult<Object>(handleValidation, "Validations Error");

    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleBusinessErrors(BusinessException businessException) {
        return new ErrorDataResult<Object>(businessException.getMessage(), "Business Error");
    }
}
