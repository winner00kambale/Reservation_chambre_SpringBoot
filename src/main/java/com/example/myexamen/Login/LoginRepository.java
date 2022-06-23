package com.example.myexamen.Login;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface LoginRepository extends CrudRepository<Login,Integer> {
    @Query(value = "select 0 as login,'' as password,login.id from login where login.mail=? and login.password=?",nativeQuery = true)
    Optional<Login> login(String mail, String password);
    @Query(value = "select * from login where mail=? and password =?",nativeQuery = true)
    Optional<Login> testmail(String mail, String password);
}
