package com.ctmhoang.userfront.service;

import com.ctmhoang.userfront.domain.PrimaryAccount;
import com.ctmhoang.userfront.domain.PrimaryTransaction;
import com.ctmhoang.userfront.domain.SavingsAccount;
import com.ctmhoang.userfront.domain.SavingsTransaction;

import java.util.List;

public interface ITransactionService
{
    List<PrimaryTransaction> findPrimaryTransactions(String username);
    List<SavingsTransaction> findSavingsTransactions(String username);
    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);
    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);
    void saveWithdrawDepositTransaction(PrimaryTransaction primaryTransaction);
    void saveWithdrawDepositTransaction(SavingsTransaction savingsTransaction);

  void betweenAccountsTransfer(
      String from,
      String to,
      PrimaryAccount primaryAccount,
      SavingsAccount savingsAccount,
      String amount) throws Exception;
}
