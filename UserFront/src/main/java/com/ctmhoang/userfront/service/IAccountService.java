package com.ctmhoang.userfront.service;

import com.ctmhoang.userfront.domain.PrimaryAccount;
import com.ctmhoang.userfront.domain.SavingsAccount;

public interface IAccountService
{
    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
}
