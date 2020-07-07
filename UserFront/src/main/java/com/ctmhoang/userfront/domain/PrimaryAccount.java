package com.ctmhoang.userfront.domain;

import java.math.BigDecimal;
import java.util.List;

public class PrimaryAccount
{
    private int accNum;

    private Long id;
    private BigDecimal accBal;

    private List<PrimaryTransaction> primaryTransactionList;

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

    public List<PrimaryTransaction> getPrimaryTransactionList()
    {
        return primaryTransactionList;
    }

    public void setPrimaryTransactionList(List<PrimaryTransaction> primaryTransactionList)
    {
        this.primaryTransactionList = primaryTransactionList;
    }
}
