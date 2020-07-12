package com.ctmhoang.userfront.domain.security;

import com.ctmhoang.userfront.domain.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usr_role")
public class UserRole {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long userRoleId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "usr_id")
  private User usr;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "role_id")
  private Role role;

  public UserRole() {}

  public UserRole(User usr, Role role) {
    this.usr = usr;
    this.role = role;
  }
}
