package com.ctmhoang.userfront.service;

import com.ctmhoang.userfront.domain.security.Role;

public interface IRoleService {
  Role getUserRole();

  void createBasicRoles();
}
