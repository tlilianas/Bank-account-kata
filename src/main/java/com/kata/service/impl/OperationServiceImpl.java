package com.kata.service.impl;

import com.kata.BankAccount.BankAccountApplication;
import com.kata.exception.OperationException;
import com.kata.model.Account;
import com.kata.model.Operation;
import com.kata.repository.AccountRepository;
import com.kata.repository.OperationRepository;
import com.kata.service.AccountService;
import com.kata.service.OperationService;
import com.kata.util.Constants;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Service("operationService")
public class OperationServiceImpl implements OperationService {

    private static final Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);
    private SessionFactory hibernateFactory;

    @Autowired
    OperationService operationService;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    AccountService accountService;


    @Autowired
    public OperationServiceImpl(EntityManagerFactory factory) {
        //TODO: to impl in util class
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
    }

    // can be implemented in the interface
    @Override
    public Operation saveOperation(Operation operation) throws OperationException{
        return operationRepository.save(operation);
    }

    @Override
    public void deposit(long accountId, double amount)  throws OperationException {
        Account account = accountService.getAccountbyId(accountId);
        account.deposit(amount);
        Operation operation = new Operation(account, new Date(), amount, Constants.DEPOSIT, accountService.getBalance(accountId));
        operationService.saveOperation(operation);
        accountService.saveAccount(account);
    }

    @Override
    public void withdrawal(long accountId, double amount) throws OperationException{
        Account account = accountService.getAccountbyId(accountId);
        account.withdrawal(amount);
        Operation operation = new Operation(account, new Date(), amount, Constants.WITHDRAWAL, accountService.getBalance(accountId));
        operationService.saveOperation(operation);
        accountService.saveAccount(account);
    }

    @Override
    public Operation getOperationById(long operationId) throws OperationException{
        return operationRepository.findOne(operationId);
    }

    @Override
    public List<Operation> getOperationByAccount(@NotNull final long accountId) throws OperationException{
        // TODO: if no account no need to go further
        Account account = accountService.getAccountbyId(accountId);
        Session session = hibernateFactory.openSession();
        Criteria criteria = session.createCriteria(Operation.class);

        criteria.add(Restrictions.eq("account", account));
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.alias(Projections.property("id"),"Operation ID"));
        projectionList.add(Projections.alias(Projections.property("date"),"Timestamp"));
        projectionList.add(Projections.property("amount"), "amount");
        projectionList.add(Projections.property("type"), "Operation Type");
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Operation> operations = criteria.list();
        return operations;

    }



}
