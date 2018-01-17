package com.kata.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kata.exception.OperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ACCOUNT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Account.class);


    @Id
    @GeneratedValue
    private long id;
    private double balance;
    private String currency;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Operation> operations;
    private Customer customer;

    public Account(long id, double balance, String currency, List<Operation> operations, Customer customer) {
        this.id = id;
        this.balance = balance;
        this.currency = currency;
        this.operations = operations;
        this.customer = customer;
    }

    public Account() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }



    public void deposit(double amount) throws OperationException {
        if (amount < 0) {
            throw new OperationException("Invalid amount");
        }
        this.balance += amount;
        logger.info("Deposit of : " +  this.balance);
    }

    public void withdrawal(double amount) throws OperationException {
        if (amount < 0) {
            throw new OperationException("Invalid amount");
        } else if (amount > this.balance) {
            throw new OperationException("Not enough balance to withdraw the requested amount");
        }
        this.balance -= amount;
        logger.info("withdrawal of : "+ this.balance);
    }



}
