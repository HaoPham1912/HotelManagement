package com.h2.hotelmangement.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customertypes")
public class CustomerTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "typename")
    private String typename;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerTypes")
    private Set<Customers> customers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public Set<Customers> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customers> customers) {
        this.customers = customers;
    }
}
