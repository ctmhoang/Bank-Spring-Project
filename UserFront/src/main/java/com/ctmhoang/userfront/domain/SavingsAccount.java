package com.ctmhoang.userfront.domain;

import java.math.BigDecimal;
import java.util.List;

public class SavingsAccount
{
    private int accNum;

    private Long id;
    private BigDecimal accBal;

    private List<SavingsTransaction> savingsTransactions;

    public int getAccNum()
    {
        return accNum;
    }

    public void setAccNum(int accNum)
    {
        this.accNum = accNum;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public BigDecimal getAccBal()
    {
        return accBal;
    }

    public void setAccBal(BigDecimal accBal)
    {
        this.accBal = accBal;
    }

    public List<SavingsTransaction> getSavingsTransactions()
    {
        return savingsTransactions;
    }

    public void setSavingsTransactions(List<SavingsTransaction> savingsTransactions)
    {
        this.savingsTransactions = savingsTransactions;
    }
}
