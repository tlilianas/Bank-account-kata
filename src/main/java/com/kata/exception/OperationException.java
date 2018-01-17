package com.kata.exception;

public class OperationException extends Exception {


        private static final long serialVersionUID = 1L;
        private String errorMessage;

        public String getErrorMessage() {
            return errorMessage;
        }

        public OperationException(String errorMessage) {
            super(errorMessage);
            this.errorMessage = errorMessage;
        }

        public OperationException() {
            super();
        }

}
