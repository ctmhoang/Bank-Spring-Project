package com.ctmhoang.userfront.service.impl;

import com.ctmhoang.userfront.domain.PrimaryTransaction;
import com.ctmhoang.userfront.domain.SavingsTransaction;
import com.ctmhoang.userfront.domain.User;
import com.ctmhoang.userfront.service.ITransactionService;
import com.ctmhoang.userfront.service.IUserService;
import com.ctmhoang.userfront.dao.PrimaryTransactionDao;
import com.ctmhoang.userfront.dao.SavingsTransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {

  @Autowired private IUserService userService;

  @Autowired private PrimaryTransactionDao primaryTransactionDao;

  @Autowired private SavingsTransactionDao savingsTransactionDao;

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
}
