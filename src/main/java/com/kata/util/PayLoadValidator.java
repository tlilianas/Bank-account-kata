package com.kata.util;

import com.kata.model.Operation;

public class PayLoadValidator {
    public static boolean validateCreatePayload(Operation operation){
        if (operation.getId() > 0){
            return false;
        }
        return true;
    }

}
