package com.ctmhoang.userfront.dao;

import com.ctmhoang.userfront.domain.PrimaryAccount;
import com.ctmhoang.userfront.domain.PrimaryTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {
  List<PrimaryTransaction> findByPrimAcc(PrimaryAccount primaryAccount);
}
