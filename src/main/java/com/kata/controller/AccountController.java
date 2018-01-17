package com.kata.controller;



import com.kata.exception.OperationException;
import com.kata.model.Account;
import com.kata.model.Operation;
import com.kata.model.Response;
import com.kata.service.AccountService;
import com.kata.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value= "/accounts")
@RestController
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private OperationService operationService;

    @RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllAccounts(){
        logger.info("Listing all available accounts");
        return new ResponseEntity<List<Account>>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Account> getAccountById(@PathVariable("id") long id){
        logger.info("Getting account : " + id);
        Account account = accountService.getAccountbyId(id);
        if(account == null || account.getId() <= 0){
            //
        }
        return new ResponseEntity<Account>(accountService.getAccountbyId(id), HttpStatus.OK);
    }

    @RequestMapping(value="/{accountId}/deposit", method = RequestMethod.POST)
    public ResponseEntity<Response> makeDeposit(@PathVariable("accountId") long accountId,@RequestBody Operation operation) throws OperationException {
        logger.info("Making deposit of : " + operation.getAmount() + " for account n°: " + accountId);
        //   if(!PayLoadValidator.validateCreatePayload(payload)){
        // throw malformed exception
        // }


        operationService.deposit(accountId, operation.getAmount() );
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Deposit sucessfully made"), HttpStatus.OK);
    }

    @RequestMapping(value="/{accountId}/withdrawal", method = RequestMethod.POST)
    public ResponseEntity<Response> makeWithdrawal(@PathVariable("accountId") long accountId, @RequestBody Operation operation) throws OperationException {
        logger.info("Withdrawal of : " + operation.getAmount() + " for account n°: " + accountId);
        operationService.withdrawal(accountId, operation.getAmount());
        return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "Withdrawal sucessfully made"), HttpStatus.OK);
    }
}