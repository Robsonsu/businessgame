package com.example.kaua.businessgame.Response;

/**
 * Created by Kauã on 10/09/2017.
 */

public class RespostaServidor {

    private String result;
    private boolean sucess;
    private String data;
    private String message;

    public RespostaServidor(){}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
