package com.adresDefterim.business.concrates;

import com.adresDefterim.business.abstracts.UserService;
import com.adresDefterim.business.abstracts.UserService;
import com.adresDefterim.business.dto.user.UserGetDto;
import com.adresDefterim.business.dto.user.UserListDto;
import com.adresDefterim.business.request.user.CreateUserRequest;
import com.adresDefterim.business.request.user.DeleteUserRequest;
import com.adresDefterim.business.request.user.UpdateUserRequest;
import com.adresDefterim.core.exception.BusinessException;
import com.adresDefterim.core.mapping.ModelMapperService;
import com.adresDefterim.core.result.DataResult;
import com.adresDefterim.core.result.Result;
import com.adresDefterim.core.result.SuccessDataResult;
import com.adresDefterim.core.result.SuccessResult;
import com.adresDefterim.dataAccess.abstracts.UserDao;
import com.adresDefterim.entities.concrates.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/*
 *       Kurallar
 * kullanıcı adı ve mail benzersiz olucak
 * şifre ve email validation ile kontrol edilecek
 * kullanıcı id varmı kontrolu yapılacak
 *
 * */


@Service
public class UserManager implements UserService {

    private BCryptPasswordEncoder passwordEncoder;
    private UserDao userDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public UserManager(UserDao userDao, ModelMapperService modelMapperService,BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.modelMapperService = modelMapperService;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public Result add(CreateUserRequest createUserRequest) throws BusinessException {

        this.checkIfMatchingUserNameByUserName(createUserRequest.getUserName());
        this.checkIfMatchingMailByMail(createUserRequest.getMail());
        User response = this.modelMapperService.forRequest().map(createUserRequest, User.class);

        response.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        response.setCreateDate(new Date());
        this.userDao.save(response);
        return new SuccessResult("Kullanıcı Başarıyla Kaydedildi");
    }

    @Override
    public Result update(UpdateUserRequest updateUserRequest) throws BusinessException {

        this.checkIfUserIdByUserId(updateUserRequest.getUserId());

        User response = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
        this.checkIfUserNameAndMailNotChange(response.getUserId(), response.getUserName(), response.getMail());

        this.userDao.save(response);
        return new SuccessResult("Kullanıcı Başarıyla Güncellendi");
    }

    @Override
    public Result delete(DeleteUserRequest deleteUserRequest) throws BusinessException {

        this.checkIfUserIdByUserId(deleteUserRequest.getUserId());

        this.userDao.deleteById(deleteUserRequest.getUserId());
        return new SuccessResult("Kullanıcı Başarıyla Silindi");
    }

    @Override
    public DataResult<List<UserListDto>> getAll() {

        List<User> request = this.userDao.findAll();
        List<UserListDto> response = request.stream()
                .map(user -> this.modelMapperService.forDto()
                        .map(user, UserListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<UserListDto>>(response, "Kayıtlar Listelendi");
    }

    @Override
    public DataResult<UserGetDto> getById(Long userId) throws BusinessException {
        this.checkIfUserIdByUserId(userId);

        User request = this.userDao.getByUserId(userId);
        UserGetDto response = this.modelMapperService.forDto().map(request, UserGetDto.class);

        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<UserGetDto> getByUserName(String userName) throws BusinessException {
        this.checkIfUserIdByUserName(userName);

        User request = this.userDao.getByUserName(userName);
        UserGetDto response = this.modelMapperService.forDto().map(request, UserGetDto.class);

        return new SuccessDataResult<>(response);
    }

    private void checkIfMatchingUserNameByUserName(String userName) throws BusinessException {
        if (this.userDao.existsByUserName(userName)) {
            throw new BusinessException("Bu kullanıcı adı kullanılıyor lütfen başka kullanıcı adı giriniz...");
        }
    }

    private void checkIfMatchingMailByMail(String mail) throws BusinessException {
        if (this.userDao.existsByMail(mail)) {
            throw new BusinessException("Bu mail adresi kullanılıyor lütfen başka mail adresi giriniz...");
        }
    }

    public void checkIfUserIdByUserId(Long userId) throws BusinessException {
        if (!this.userDao.existsById(userId)) {
            throw new BusinessException("Bu kullanıcı bulunamadı lütfen doğru kullanıcı id giriniz...");
        }
    }

    public void checkIfUserIdByUserName(String userName) throws BusinessException {
        if (!this.userDao.existsByUserName(userName)) {
            throw new BusinessException("Bu kullanıcı bulunamadı lütfen doğru kullanıcı id giriniz...");
        }
    }

    private void checkIfUserNameAndMailNotChange(long userId, String userName, String mail) throws BusinessException {
        this.checkIfUserIdByUserId(userId);
        User user = this.userDao.getByUserId(userId);
        if (!(user.getUserName().equals(userName) && user.getMail().equals(mail))) {
            throw new BusinessException("Kullanıcı adı ve mail degiştirilemez...");
        }
    }


}
