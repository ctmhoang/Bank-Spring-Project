package com.ctmhoang.userfront.service;

import com.ctmhoang.userfront.domain.User;

public interface IUserService
{
    User findByUserName(String userName);

    User findByEmail(String email);

    boolean isUserExists(String name, String email);

    boolean checkUserNameExists(String userName);

    boolean checkEmailExists(String email);

    void save(User user);
}
