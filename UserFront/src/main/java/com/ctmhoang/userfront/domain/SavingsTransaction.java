package com.ctmhoang.userfront.domain;

import java.math.BigDecimal;
import java.util.Date;

public class SavingsTransaction
{
    private Long id;
    private Date date;
    private String description;
    private String type;
    private String status;
    private BigDecimal availBal;
    private SavingsAccount savAcc;

    public SavingsTransaction()
    {
    }

    public SavingsTransaction(Date date,
                              String description,
                              String type,
                              String status,
                              BigDecimal availBal,
                              SavingsAccount savAcc)
    {
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.availBal = availBal;
        this.savAcc = savAcc;
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

    public SavingsAccount getSavAcc()
    {
        return savAcc;
    }

    public void setSavAcc(SavingsAccount savAcc)
    {
        this.savAcc = savAcc;
    }
}
