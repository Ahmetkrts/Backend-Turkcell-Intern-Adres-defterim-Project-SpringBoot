package com.adresDefterim.dataAccess.abstracts;

import com.adresDefterim.entities.concrates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {

    boolean existsByUserId(Long userId);
    boolean existsByUserName(String userName);
    boolean existsByMail(String mail);

    User getByUserName(String userName);
    User getByUserId(Long userId);




}
