package com.ctmhoang.userfront.service;

import com.ctmhoang.userfront.domain.PrimaryAccount;
import com.ctmhoang.userfront.domain.SavingsAccount;

import java.security.Principal;

public interface IAccountService
{
    PrimaryAccount createPrimaryAccount();

    SavingsAccount createSavingsAccount();

    void deposit(String type, double parseDouble, Principal principal);

    void withdraw(String type, double parseDouble, Principal principal);

    PrimaryAccount getPrimaryAccount(int accNum);

    SavingsAccount getSavingsAccount(int accNum);
}
