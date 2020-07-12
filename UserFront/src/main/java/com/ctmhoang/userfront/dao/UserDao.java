package com.ctmhoang.userfront.dao;

import com.ctmhoang.userfront.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
  User findByUsrName(String usrName);

  User findByEmail(String email);
}
