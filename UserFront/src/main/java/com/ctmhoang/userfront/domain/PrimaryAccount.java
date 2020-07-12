package com.ctmhoang.userfront.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class PrimaryAccount {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private int accNum;

  private BigDecimal accBal;

  @OneToMany(mappedBy = "primAcc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnore
  private List<PrimaryTransaction> primaryTransactionList;
}
