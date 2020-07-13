package com.ctmhoang.userfront.service;

import com.ctmhoang.userfront.domain.*;

import java.security.Principal;
import java.util.List;

public interface ITransactionService {
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
      String amount)
      throws Exception;

  List<Recipient> findRecipients(Principal principal);

  void saveRecipient(Recipient recipient);

  Recipient findRecipientByName(String name);

  void deleteRecipientByName(String name);
}
