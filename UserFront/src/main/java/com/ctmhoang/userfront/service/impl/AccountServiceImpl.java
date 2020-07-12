package com.ctmhoang.userfront.service.impl;

import com.ctmhoang.userfront.dao.PrimaryAccountDao;
import com.ctmhoang.userfront.dao.SavingsAccountDao;
import com.ctmhoang.userfront.domain.PrimaryAccount;
import com.ctmhoang.userfront.domain.SavingsAccount;
import com.ctmhoang.userfront.service.IAccountService;
import com.ctmhoang.userfront.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements IAccountService {
  @Autowired private PrimaryAccountDao primaryAccountDao;

  @Autowired private SavingsAccountDao savingsAccountDao;

  @Autowired private IUserService userService;
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

  private int accGen() {
    return ++nextAccNum;
  }
}
