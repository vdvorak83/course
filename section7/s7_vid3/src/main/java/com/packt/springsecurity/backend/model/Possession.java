package com.packt.springsecurity.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "possession")
public class Possession {
    private Long id;
    private String name;
    private Account owner;

    public Possession() {
    }

    public Possession(Long id) {
        this.id = id;
    }

    public Possession(Possession orig) {
        this.id = orig.id;
        this.name = orig.name;
        this.owner = orig.owner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    private void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

}
