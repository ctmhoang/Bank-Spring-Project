package com.ctmhoang.userfront.dao;

import com.ctmhoang.userfront.domain.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long> {
  PrimaryAccount findByAccNum(int accountNumber);
}
