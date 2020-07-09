package com.ctmhoang.userfront.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class PrimaryAccount
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int accNum;

    private BigDecimal accBal;

    @OneToMany(mappedBy = "primAcc", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
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
