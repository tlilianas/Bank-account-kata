package com.kata.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="OPERATION")
public class Operation implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @Column(name= "Date")
    private Date date;
    private double amount;
    private double balanceAtDeposit;
    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private Account account;
    private String type;

    public Operation(){
        super();
    }

    public Operation(Account account, Date date, double amount, String type, double balanceAtDeposit){
        this.account = account;
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.balanceAtDeposit = balanceAtDeposit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBalanceAtDeposit() {
        return balanceAtDeposit;
    }

    public void setBalanceAtDeposit(double balanceAtDeposit) {
        this.balanceAtDeposit = balanceAtDeposit;
    }
}
