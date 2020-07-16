package com.ctmhoang.userfront.service;

import com.ctmhoang.userfront.domain.User;
import com.ctmhoang.userfront.domain.security.UserRole;

import java.util.List;
import java.util.Set;

public interface IUserService {
  User findByUserName(String userName);

  User findByEmail(String email);

  boolean isUserExists(String name, String email);

  boolean checkUserNameExists(String userName);

  boolean checkEmailExists(String email);

  void save(User user);

  User create(User user, Set<UserRole> userRoles);

  List<User> fetchAll();

  void disable(String username);

  void enable(String username);
}
