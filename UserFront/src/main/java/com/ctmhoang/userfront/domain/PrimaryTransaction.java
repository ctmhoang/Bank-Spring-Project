package com.ctmhoang.userfront.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class PrimaryTransaction {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private double amount;

  private Date date;
  private String description;
  private String type;
  private String status;
  private BigDecimal availBal;

  @ManyToOne
  @JoinColumn(name = "primary_account_id")
  private PrimaryAccount primAcc;

  public PrimaryTransaction(
      double amount,
      Date date,
      String description,
      String type,
      String status,
      BigDecimal availBal,
      PrimaryAccount primAcc) {
    this.amount = amount;
    this.date = date;
    this.description = description;
    this.type = type;
    this.status = status;
    this.availBal = availBal;
    this.primAcc = primAcc;
  }
}
