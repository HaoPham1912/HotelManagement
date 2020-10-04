package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Accounts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountid")
    private Long accountid;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", length = 50, nullable = false)
    private String password;

    @Column(name = "status")
    private boolean status;

    @ManyToMany(mappedBy = "accounts")
    private Set<Roles> roleEntities;

    public Long getAccountid() {
        return accountid;
    }

    public void setAccountid(Long accountid) {
        this.accountid = accountid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Roles> getRoleEntities() {
        return roleEntities;
    }

    public void setRoleEntities(Set<Roles> roleEntities) {
        this.roleEntities = roleEntities;
    }
}

