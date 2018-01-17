package com.kata.service;

import com.kata.exception.OperationException;
import com.kata.model.Operation;

import java.util.List;

public interface OperationService {

    public void deposit(long accountId, double amount) throws OperationException;
    public void withdrawal(long accountId, double amount) throws OperationException;
    public Operation getOperationById(long operationId) throws OperationException;
    public List<Operation> getOperationByAccount(long accountId) throws OperationException;
    public Operation saveOperation(Operation operation) throws OperationException;

}
