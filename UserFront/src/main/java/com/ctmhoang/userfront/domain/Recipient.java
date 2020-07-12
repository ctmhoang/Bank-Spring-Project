package com.ctmhoang.userfront.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Recipient {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String email;
  private String phone;
  private String accountNum;
  private String description;

  @ManyToOne
  @JoinColumn(name = "usr_Id")
  @JsonIgnore
  private User usr;
}
