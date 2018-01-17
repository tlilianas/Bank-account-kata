package com.kata.service;

import com.kata.model.Customer;

public interface CustomerService {
    public Customer getCustomerbyId(long id);
    public Customer saveCustomer(Customer customer);
    public void removeCustomer(Customer customer);
}
