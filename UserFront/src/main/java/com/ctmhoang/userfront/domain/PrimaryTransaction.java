package com.ctmhoang.userfront.domain;

import java.math.BigDecimal;
import java.util.Date;

public class PrimaryTransaction
{
    private double amount;

    private Long id;
    private Date date;
    private String description;
    private String type;
    private String status;
    private BigDecimal availBal;
    private PrimaryAccount primAcc;

    public PrimaryTransaction()
    {
    }

    public PrimaryTransaction(double amount,
                              Date date,
                              String description,
                              String type,
                              String status,
                              BigDecimal availBal, PrimaryAccount primAcc)
    {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.availBal = availBal;
        this.primAcc = primAcc;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public BigDecimal getAvailBal()
    {
        return availBal;
    }

    public void setAvailBal(BigDecimal availBal)
    {
        this.availBal = availBal;
    }

    public PrimaryAccount getPrimAcc()
    {
        return primAcc;
    }

    public void setPrimAcc(PrimaryAccount primAcc)
    {
        this.primAcc = primAcc;
    }
}
