package com.ctmhoang.userfront.service.impl;

import com.ctmhoang.userfront.dao.*;
import com.ctmhoang.userfront.domain.*;
import com.ctmhoang.userfront.service.IAccountService;
import com.ctmhoang.userfront.service.ITransactionService;
import com.ctmhoang.userfront.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {

  @Autowired private IUserService userService;

  @Autowired private PrimaryTransactionDao primaryTransactionDao;

  @Autowired private SavingsTransactionDao savingsTransactionDao;

  @Autowired private PrimaryAccountDao primaryAccountDao;

  @Autowired private SavingsAccountDao savingsAccountDao;

  @Autowired private RecipientDao recipientDao;

  @Autowired private IAccountService accountService;

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
      String amount)
      throws Exception {
    if (from.equalsIgnoreCase("Primary") && to.equalsIgnoreCase("Savings")) {
      var tmp = primaryAccount.getAccBal().subtract(new BigDecimal(amount));
      if (tmp.compareTo(BigDecimal.ZERO) < 0) return;

      primaryAccount.setAccBal(tmp);
      savingsAccount.setAccBal(savingsAccount.getAccBal().add(new BigDecimal(amount)));
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

      var tmp = savingsAccount.getAccBal().subtract(new BigDecimal(amount));
      if (tmp.compareTo(BigDecimal.ZERO) < 0) return;
      savingsAccount.setAccBal(tmp);
      primaryAccount.setAccBal(primaryAccount.getAccBal().add(new BigDecimal(amount)));
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

  @Override
  public List<Recipient> findRecipients(Principal principal) {
    User user = userService.findByUserName(principal.getName());
    return recipientDao.findRecipientsByUsr(user);
  }

  @Override
  public void saveRecipient(Recipient recipient) {
    recipientDao.save(recipient);
  }

  @Override
  public Recipient findRecipientByName(String name) {
    return recipientDao.findRecipientByName(name);
  }

  @Override
  @Transactional
  public void deleteRecipientByName(String name) {
    recipientDao.deleteByName(name);
  }

  @Override
  public void transferTo(
      Recipient recipient,
      String type,
      String amount,
      PrimaryAccount primAcc,
      SavingsAccount saveAcc) {

    var date = new Date();

    if (type.equalsIgnoreCase("Primary")) {

      var tmp = primAcc.getAccBal().subtract(new BigDecimal(amount));
      if (tmp.compareTo(BigDecimal.ZERO) < 0) return;

      primAcc.setAccBal(primAcc.getAccBal().subtract(new BigDecimal(amount)));
      primaryAccountDao.save(primAcc);

      var primTrans =
          new PrimaryTransaction(
              Double.parseDouble(amount),
              date,
              "Transfer to recipient " + recipient.getName(),
              "Transfer",
              "Finished",
              primAcc.getAccBal(),
              primAcc);

      primaryTransactionDao.save(primTrans);
    } else if (type.equalsIgnoreCase("Savings")) {

      var tmp = saveAcc.getAccBal().subtract(new BigDecimal(amount));
      if (tmp.compareTo(BigDecimal.ZERO) < 0) return;

      saveAcc.setAccBal(saveAcc.getAccBal().subtract(new BigDecimal(amount)));
      savingsAccountDao.save(saveAcc);

      var saveTrans =
          new PrimaryTransaction(
              Double.parseDouble(amount),
              date,
              "Transfer to recipient " + recipient.getName(),
              "Transfer",
              "Finished",
              primAcc.getAccBal(),
              primAcc);

      primaryTransactionDao.save(saveTrans);
    }

    //get recipient account
    int accNum = Integer.parseInt(recipient.getAccountNum());
    if(accNum % 2 == 0){
      PrimaryAccount account = accountService.getPrimaryAccount(accNum);
      account.setAccBal(account.getAccBal().add(new BigDecimal(amount)));
      var receiveBill =
              new PrimaryTransaction(
                      Double.parseDouble(amount),
                      date,
                      "Transfer from recipient " + recipient.getName(),
                      "Transfer",
                      "Finished",
                      account.getAccBal(),
                      account);

      receiveBill.setAvailBal(account.getAccBal());
      primaryAccountDao.save(account);
      primaryTransactionDao.save(receiveBill);
    }
    else {
      SavingsAccount account = accountService.getSavingsAccount(accNum);
      account.setAccBal(account.getAccBal().add(new BigDecimal(amount)));
      var receiveBill =
              new SavingsTransaction(
                      Double.parseDouble(amount),
                      date,
                      "Transfer from recipient " + recipient.getName(),
                      "Transfer",
                      "Finished",
                      account.getAccBal(),
                      account);
      receiveBill.setAvailBal(account.getAccBal());
      savingsAccountDao.save(account);
      savingsTransactionDao.save(receiveBill);
    }
  }
}
