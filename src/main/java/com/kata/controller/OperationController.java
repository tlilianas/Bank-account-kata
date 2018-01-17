package com.kata.controller;

import com.kata.exception.OperationException;
import com.kata.model.Account;
import com.kata.model.Operation;
import com.kata.model.Response;
import com.kata.service.OperationService;
import com.kata.util.PayLoadValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value="/operations")
@RestController
public class OperationController {

    private static final Logger logger = LoggerFactory.getLogger(OperationController.class);

    @Autowired
    OperationService operationService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Operation> getOperationById(@PathVariable("id") long id) throws  OperationException{
        logger.info("Getting Operation : " + id);
        Operation operation = operationService.getOperationById(id);
        if(operation == null || operation.getId() <= 0){
            return new ResponseEntity<Operation>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Operation>(operationService.getOperationById(id), HttpStatus.OK);
    }

    @RequestMapping(value="/account/{id}", method= RequestMethod.GET)
    public ResponseEntity<List<Operation>> getOperationByAccountId(@PathVariable("id") long id) throws OperationException{
        logger.info("Getting operations related to account no°: " + id);
        List<Operation> operations = operationService.getOperationByAccount(id);
    return new ResponseEntity<List<Operation>>(operationService.getOperationByAccount(id), HttpStatus.OK);
    }





}
