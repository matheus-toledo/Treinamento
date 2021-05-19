package com.docnix.errorHandler;

public class ErrorObject {
    private String errorMessage;

    public ErrorObject() {
    }

    public ErrorObject(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Error{" +
                "\"errorMessage\":'" + errorMessage + '\'' +
                '}';
    }
}
