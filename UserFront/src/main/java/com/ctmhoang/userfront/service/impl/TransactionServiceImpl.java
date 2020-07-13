package com.ctmhoang.userfront.service.impl;

import com.ctmhoang.userfront.dao.PrimaryAccountDao;
import com.ctmhoang.userfront.dao.SavingsAccountDao;
import com.ctmhoang.userfront.domain.*;
import com.ctmhoang.userfront.service.ITransactionService;
import com.ctmhoang.userfront.service.IUserService;
import com.ctmhoang.userfront.dao.PrimaryTransactionDao;
import com.ctmhoang.userfront.dao.SavingsTransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {

  @Autowired private IUserService userService;

  @Autowired private PrimaryTransactionDao primaryTransactionDao;

  @Autowired private SavingsTransactionDao savingsTransactionDao;

  @Autowired private PrimaryAccountDao primaryAccountDao;

  @Autowired private SavingsAccountDao savingsAccountDao;

  @Override
  public List<PrimaryTransaction> findPrimaryTransactions(String username) {
    User user = userService.findByUserName(username);
    return primaryTransactionDao.findByPrimAcc(user.getPrimAcc());
  }

  @Override
  public List<SavingsTransaction> findSavingsTransactions(String username) {
    User user = userService.findByUserName(username);
    return savingsTransactionDao.findBySavAcc(user.getSaveAcc());
  }

  @Override
  public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
    primaryTransactionDao.save(primaryTransaction);
  }

  @Override
  public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
    savingsTransactionDao.save(savingsTransaction);
  }

  @Override
  public void saveWithdrawDepositTransaction(PrimaryTransaction primaryTransaction) {
    primaryTransactionDao.save(primaryTransaction);
  }

  @Override
  public void saveWithdrawDepositTransaction(SavingsTransaction savingsTransaction) {
    savingsTransactionDao.save(savingsTransaction);
  }

  @Override
  public void betweenAccountsTransfer(
      String from,
      String to,
      PrimaryAccount primaryAccount,
      SavingsAccount savingsAccount,
      String amount) throws Exception
  {
    if (from.equalsIgnoreCase("Primary") && to.equalsIgnoreCase("Savings")) {
      primaryAccount.setAccBal(
          primaryAccount.getAccBal().subtract(new BigDecimal(amount)));
      savingsAccount.setAccBal(
          savingsAccount.getAccBal().add(new BigDecimal(amount)));
      primaryAccountDao.save(primaryAccount);
      savingsAccountDao.save(savingsAccount);

      Date date = new Date();

      PrimaryTransaction primaryTransaction =
          new PrimaryTransaction(
                  Double.parseDouble(amount),
                  date,
                  "Between account transfer from " + from + " to " + to,
                  "Transfer",
                  "Finished",
                  primaryAccount.getAccBal(),
                  primaryAccount);
      primaryTransactionDao.save(primaryTransaction);
    } else if (from.equalsIgnoreCase("Savings") && to.equalsIgnoreCase("Primary")) {
      primaryAccount.setAccBal(
          primaryAccount.getAccBal().add(new BigDecimal(amount)));
      savingsAccount.setAccBal(
          savingsAccount.getAccBal().subtract(new BigDecimal(amount)));
      primaryAccountDao.save(primaryAccount);
      savingsAccountDao.save(savingsAccount);

      Date date = new Date();

      SavingsTransaction savingsTransaction =
          new SavingsTransaction(
                  Double.parseDouble(amount),
                  date,
                  "Between account transfer from " + from + " to " + to,
                  "Transfer",
                  "Finished",
                  savingsAccount.getAccBal(),
                  savingsAccount);
      savingsTransactionDao.save(savingsTransaction);
    } else {
      throw new Exception("Invalid Transfer");
    }
  }
}
