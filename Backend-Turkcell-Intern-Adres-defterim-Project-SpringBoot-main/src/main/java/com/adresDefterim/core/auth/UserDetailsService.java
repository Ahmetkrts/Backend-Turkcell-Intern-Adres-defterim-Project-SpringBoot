package com.adresDefterim.core.auth;

import java.util.ArrayList;

import com.adresDefterim.dataAccess.abstracts.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (userDao.existsByUserName(username)) {
            System.out.println("Kullanıcı Adı: " + username);
            return new User(username, userDao.getByUserName(username).getPassword(), new ArrayList<>());
        } else {
            System.out.println("Kullanıcı Adı: " + username);
            throw new UsernameNotFoundException("kullanıcı Sistemde Kayıtlı degildir.");
        }
    }
}
