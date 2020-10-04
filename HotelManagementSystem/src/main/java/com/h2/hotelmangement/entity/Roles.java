package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @Column(name = "roleid")
    private Long roleId;

    @Column(name = "rolename")
    private String roleName;

    @ManyToMany
    private Set<Accounts> accounts;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


    public Set<Accounts> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Accounts> accounts) {
        this.accounts = accounts;
    }
}
