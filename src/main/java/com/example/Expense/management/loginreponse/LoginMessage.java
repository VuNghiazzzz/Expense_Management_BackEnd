package com.example.Expense.management.loginreponse;

public class LoginMessage {

    private String message;
    private boolean status;
    private String token;

    public LoginMessage() {}

    public LoginMessage(String message, boolean status, String token) {
        this.message = message;
        this.status = status;
        this.token = token;
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

    public String getToken() {return token;}

    public void setToken(String token) {this.token = token;}
}
