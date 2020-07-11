package com.ctmhoang.userfront.service.impl;

import com.ctmhoang.userfront.dao.RoleDao;
import com.ctmhoang.userfront.domain.security.Role;
import com.ctmhoang.userfront.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class RoleService implements IRoleService
{

    @Autowired
    private RoleDao roleDao;


    @Override
    public Role getUserRole()
    {
        return roleDao.findByName("ROLE_USER");
    }

    @Override
    public void createBasicRoles()
    {
        roleDao.saveAll(Set.of(new Role(1, "ROLE_ADMIN"),new Role(2,"ROLE_USER")));
    }
}
