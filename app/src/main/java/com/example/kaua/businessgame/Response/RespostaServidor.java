package com.example.kaua.businessgame.Response;

import com.example.kaua.businessgame.GrupoPergunta;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

/**
 * Created by Kauã on 10/09/2017.
 */

public class RespostaServidor {

    @SerializedName("sucess")
    private boolean sucess;
    @SerializedName("data")
    private List<Object> data;
    @SerializedName("message")
    private String message;

    public RespostaServidor(){  }


    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public List<Object> getData() {
        return  this.data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
