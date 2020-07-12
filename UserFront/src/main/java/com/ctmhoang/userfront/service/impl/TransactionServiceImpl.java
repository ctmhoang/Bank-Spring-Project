package com.ctmhoang.userfront.service.impl;

import com.ctmhoang.userfront.domain.PrimaryTransaction;
import com.ctmhoang.userfront.domain.SavingsTransaction;
import com.ctmhoang.userfront.domain.User;
import com.ctmhoang.userfront.service.ITransactionService;
import com.ctmhoang.userfront.service.IUserService;
import com.ctmhoang.userfront.dao.PrimaryTransactionDao;
import com.ctmhoang.userfront.dao.SavingsTransactionDao;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {

  private static final Logger LOG = LoggerFactory.getLogger(TransactionServiceImpl.class);

  @Autowired private IUserService userService;

  @Autowired private PrimaryTransactionDao primaryTransactionDao;

  @Autowired private SavingsTransactionDao savingsTransactionDao;

  @Override
  public List<PrimaryTransaction> findPrimaryTransactions(String username) {
    User user = userService.findByUserName(username);

    LOG.warn(Hibernate.isInitialized(user.getPrimAcc().getPrimaryTransactionList().size())+ "");
    return user.getPrimAcc().getPrimaryTransactionList();
  }

  @Override
  public List<SavingsTransaction> findSavingsTransactions(String username) {
    User user = userService.findByUserName(username);
    return new ArrayList<>(user.getSaveAcc().getSavingsTransactions());
  }

  @Override
  public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction) {
    primaryTransactionDao.save(primaryTransaction);
  }

  @Override
  public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
    savingsTransactionDao.save(savingsTransaction);
  }
}
