package com.ctmhoang.userfront.domain.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Role
{
    @Id
    private int roleId;

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();


    public Role(int roleId, String name)
    {
        this.roleId = roleId;
        this.name = name;
    }

}