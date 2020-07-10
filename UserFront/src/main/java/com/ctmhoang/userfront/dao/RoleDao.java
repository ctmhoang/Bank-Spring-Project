package com.ctmhoang.userfront.dao;

import com.ctmhoang.userfront.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Integer>
{
    Role findByName(String name);
}
