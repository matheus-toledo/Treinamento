package com.docnix.errorHandler;

public class ErrorMessage {
    private String errorMessage;

    public ErrorMessage() {
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
