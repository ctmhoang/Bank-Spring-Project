package com.ctmhoang.userfront.service.impl;

import com.ctmhoang.userfront.dao.PrimaryAccountDao;
import com.ctmhoang.userfront.dao.SavingsAccountDao;
import com.ctmhoang.userfront.domain.*;
import com.ctmhoang.userfront.service.IAccountService;
import com.ctmhoang.userfront.service.ITransactionService;
import com.ctmhoang.userfront.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

@Service
public class AccountServiceImpl implements IAccountService {
  @Autowired private PrimaryAccountDao primaryAccountDao;

  @Autowired private SavingsAccountDao savingsAccountDao;

  @Autowired private IUserService userService;

  @Autowired private ITransactionService transactionService;

  private static int nextAccNum = 11223145;

  @Override
  public PrimaryAccount createPrimaryAccount() {
    var primAcc = new PrimaryAccount();
    primAcc.setAccBal(new BigDecimal(0.0));
    primAcc.setAccNum(accGen());
    primaryAccountDao.save(primAcc);
    return primAcc;
  }

  @Override
  public SavingsAccount createSavingsAccount() {
    var saveAcc = new SavingsAccount();
    saveAcc.setAccBal(new BigDecimal(0.0));
    saveAcc.setAccNum(accGen());
    savingsAccountDao.save(saveAcc);
    return saveAcc;
  }

  @Override
  public void deposit(String type, double parseDouble, Principal principal) {
    User user = userService.findByUserName(principal.getName());
    if (type.equalsIgnoreCase("Primary")) {
      PrimaryAccount primaryAccount = user.getPrimAcc();
      primaryAccount.setAccBal(primaryAccount.getAccBal().add(new BigDecimal(parseDouble)));
      primaryAccountDao.save(primaryAccount);

      Date date = new Date();

      var primTrans =
          new PrimaryTransaction(
              parseDouble,
              date,
              "Deposit from primary account",
              "Account",
              "Finished",
              primaryAccount.getAccBal(),
              primaryAccount);
      transactionService.savePrimaryDepositTransaction(primTrans);
    } else if (type.equalsIgnoreCase("Savings")) {
      SavingsAccount savingsAccount = user.getSaveAcc();
      savingsAccount.setAccBal(savingsAccount.getAccBal().add(new BigDecimal(parseDouble)));
      savingsAccountDao.save(savingsAccount);

      Date date = new Date();

      var saveTrans =
          new SavingsTransaction(
              parseDouble,
              date,
              "Deposit from savings account",
              "Account",
              "Finished",
              savingsAccount.getAccBal(),
              savingsAccount);

      transactionService.saveSavingsDepositTransaction(saveTrans);
    }
  }

  @Override
  public void withdraw(String type, double parseDouble, Principal principal) {
    User user = userService.findByUserName(principal.getName());
    if (type.equalsIgnoreCase("Primary")) {
      PrimaryAccount primaryAccount = user.getPrimAcc();
      primaryAccount.setAccBal(primaryAccount.getAccBal().subtract(new BigDecimal(parseDouble)));
      primaryAccountDao.save(primaryAccount);

      Date date = new Date();

      var primTrans =
          new PrimaryTransaction(
              parseDouble,
              date,
              "Withdraw from primary account",
              "Account",
              "Finished",
              primaryAccount.getAccBal(),
              primaryAccount);
      transactionService.saveWithdrawDepositTransaction(primTrans);
    } else if (type.equalsIgnoreCase("Savings")) {
      SavingsAccount savingsAccount = user.getSaveAcc();
      savingsAccount.setAccBal(savingsAccount.getAccBal().subtract(new BigDecimal(parseDouble)));
      savingsAccountDao.save(savingsAccount);

      Date date = new Date();

      var saveTrans =
          new SavingsTransaction(
              parseDouble,
              date,
              "Withdraw from savings account",
              "Account",
              "Finished",
              savingsAccount.getAccBal(),
              savingsAccount);
      transactionService.saveWithdrawDepositTransaction(saveTrans);
    }
  }

  private int accGen() {
    return ++nextAccNum;
  }
}
