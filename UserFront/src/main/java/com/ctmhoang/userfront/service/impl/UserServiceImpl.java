package com.ctmhoang.userfront.service.impl;

import com.ctmhoang.userfront.dao.RoleDao;
import com.ctmhoang.userfront.dao.UserDao;
import com.ctmhoang.userfront.domain.User;
import com.ctmhoang.userfront.domain.security.UserRole;
import com.ctmhoang.userfront.service.IAccountService;
import com.ctmhoang.userfront.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional

public class UserServiceImpl implements IUserService
{
    private static final Logger LOG = LoggerFactory.getLogger(IUserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    @Autowired
    private IAccountService accountService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User create(User user , Set<UserRole> userRoles)
    {
        User localUser = userDao.findByUsrName(user.getUsrName());
        if(localUser!= null)
            LOG.info("User with username {} is already existed" , user.getUsername());
        else {
            String encryptedPwd = passwordEncoder.encode(user.getPassword());
            user.setPwd(encryptedPwd);

            userRoles.forEach(ur -> roleDao.save(ur.getRole()));

            user.getUserRoles().addAll(userRoles);

            user.setPrimAcc(accountService.createPrimaryAccount());
            user.setSaveAcc(accountService.createSavingsAccount());

        }
        return userDao.save(user);
    }

    @Override
    public void save(User user)
    {
        userDao.save(user);
    }

    @Override
    public User findByUserName(String userName)
    {
        return userDao.findByUsrName(userName);
    }

    @Override
    public User findByEmail(String email)
    {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean isUserExists(String name, String email)
    {
        return checkUserNameExists(name) || checkEmailExists(email);
    }

    @Override
    public boolean checkUserNameExists(String name)
    {
        return findByUserName(name) != null;
    }

    @Override
    public boolean checkEmailExists(String email)
    {
        return findByEmail(email) != null;
    }

}
