package com.ctmhoang.userfront.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class SavingsTransaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;
    private String description;
    private String type;
    private String status;
    private BigDecimal availBal;

    @ManyToOne
    @JoinColumn(name = "savings_account_id")
    private SavingsAccount savAcc;


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
}
