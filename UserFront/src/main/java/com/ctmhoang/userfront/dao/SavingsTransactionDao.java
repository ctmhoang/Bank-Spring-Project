package com.ctmhoang.userfront.dao;

import com.ctmhoang.userfront.domain.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Long> {
//    List<SavingsTransaction> getAll();
}
