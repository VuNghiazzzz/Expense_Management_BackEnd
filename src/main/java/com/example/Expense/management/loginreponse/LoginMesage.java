package com.example.Expense.management.loginreponse;

public class LoginMesage {

    private String message;
    private boolean status;

    public LoginMesage() {}

    public LoginMesage(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
