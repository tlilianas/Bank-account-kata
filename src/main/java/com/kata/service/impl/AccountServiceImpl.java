package com.kata.service.impl;

import com.kata.model.Account;
import com.kata.model.Operation;
import com.kata.repository.AccountRepository;
import com.kata.service.AccountService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private SessionFactory hibernateFactory;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
    }

    @Override
    public Account getAccountbyId(long id){
        return accountRepository.getOne(id);
    }

    @Override
    public List<Account> getAllAccounts(){ return accountRepository.findAll(); }
;
    @Override
    public Account saveAccount(Account account){
        return accountRepository.save(account);
    }


    @Override
    public void deleteAccount(Account account){
        accountRepository.delete(account);
    }

    @Override
    public double getBalance(@NotNull final long accountId){
        Session session = hibernateFactory.openSession();
        Criteria criteria = session.createCriteria(Account.class);
        criteria.add(Restrictions.eq("id", accountId));
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.alias(Projections.property("balance"),"Balance"));
        criteria.setProjection(projectionList);
        criteria.list();

        return Double.parseDouble(criteria.list().get(0).toString());
    }

}

