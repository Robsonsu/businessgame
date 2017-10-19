package com.example.kaua.businessgame.Response;

/**
 * Created by Kauã on 10/09/2017.
 */

public class ResponseEfetuarLogin {


    private String sucess;
    private DataEfetuarLogin data;
    private String message;

    public ResponseEfetuarLogin(){}


    public String isSucess() {
        return sucess;
    }

    public void setSucess(String sucess) {
        this.sucess = sucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataEfetuarLogin getData() {
        return data;
    }

    public void setData(DataEfetuarLogin data) {
        this.data = data;
    }
}
