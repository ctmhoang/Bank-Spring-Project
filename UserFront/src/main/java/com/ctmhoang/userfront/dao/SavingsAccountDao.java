package com.ctmhoang.userfront.dao;

import com.ctmhoang.userfront.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount,Long>
{
    SavingsAccount findByAccNum(int accountNumber);
}
