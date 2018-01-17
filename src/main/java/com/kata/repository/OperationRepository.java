package com.kata.repository;

import com.kata.model.Account;
import com.kata.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("operationRepository")
public interface OperationRepository extends JpaRepository<Operation, Long> {

}