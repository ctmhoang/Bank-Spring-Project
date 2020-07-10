package com.ctmhoang.userfront.domain;

import com.ctmhoang.userfront.domain.security.Authority;
import com.ctmhoang.userfront.domain.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "users")
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usr", nullable = false, updatable = false)
    private Long usrID;

    private boolean enabled = true;

    private String usrName;
    private String pwd;
    private String fstName;
    private String lstName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    private String phone;

    @OneToOne
    private PrimaryAccount primAcc;
    @OneToOne
    private SavingsAccount saveAcc;

    @OneToMany(mappedBy = "usr", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Appointment> appointmentList;

    @OneToMany(mappedBy = "usr", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recipient> recipientList;

    @OneToMany(mappedBy = "usr",cascade =CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private final Set<UserRole> userRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return userRoles.stream().map(userRole -> new Authority(userRole.getRole().getName())).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public String getPassword()
    {
        return pwd;
    }

    @Override
    public String getUsername()
    {
        return usrName;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRoles()
    {
        return userRoles;
    }

    public Long getUsrID()
    {
        return usrID;
    }

    public void setUsrID(Long usrID)
    {
        this.usrID = usrID;
    }

    public String getUsrName()
    {
        return usrName;
    }

    public void setUsrName(String usrName)
    {
        this.usrName = usrName;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public String getFstName()
    {
        return fstName;
    }

    public void setFstName(String fstName)
    {
        this.fstName = fstName;
    }

    public String getLstName()
    {
        return lstName;
    }

    public void setLstName(String lstName)
    {
        this.lstName = lstName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public PrimaryAccount getPrimAcc()
    {
        return primAcc;
    }

    public void setPrimAcc(PrimaryAccount primAcc)
    {
        this.primAcc = primAcc;
    }

    public SavingsAccount getSaveAcc()
    {
        return saveAcc;
    }

    public void setSaveAcc(SavingsAccount saveAcc)
    {
        this.saveAcc = saveAcc;
    }

    public List<Appointment> getAppointmentList()
    {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList)
    {
        this.appointmentList = appointmentList;
    }

    public List<Recipient> getRecipientList()
    {
        return recipientList;
    }

    public void setRecipientList(List<Recipient> recipientList)
    {
        this.recipientList = recipientList;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "enabled=" + enabled +
                ", usrID=" + usrID +
                ", usrName='" + usrName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", fstName='" + fstName + '\'' +
                ", lstName='" + lstName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", primAcc=" + primAcc +
                ", saveAcc=" + saveAcc +
                ", appointmentList=" + appointmentList +
                ", recipientList=" + recipientList +
                '}';
    }
}
