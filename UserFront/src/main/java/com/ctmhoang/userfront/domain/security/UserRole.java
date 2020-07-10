package com.ctmhoang.userfront.domain.security;

import com.ctmhoang.userfront.domain.User;

import javax.persistence.*;

@Entity
@Table(name = "usr_role")
public class UserRole
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id")
    private User usr;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public UserRole()
    {
    }

    public UserRole(User usr, Role role)
    {
        this.usr = usr;
        this.role = role;
    }

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUsr()
    {
        return usr;
    }

    public void setUsr(User user)
    {
        this.usr = user;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }
}
