package com.ctmhoang.userfront.domain;

import com.ctmhoang.userfront.domain.security.Authority;
import com.ctmhoang.userfront.domain.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@Entity
@NoArgsConstructor
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
    @EqualsAndHashCode.Exclude
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
}
