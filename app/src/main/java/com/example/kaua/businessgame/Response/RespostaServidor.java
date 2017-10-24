package com.example.kaua.businessgame.Response;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kauã on 10/09/2017.
 */

public class RespostaServidor {

    @SerializedName("sucess")
    private boolean sucess;

    @SerializedName("data")
    private JsonArray data;

    @SerializedName("message")
    private String message;

    public RespostaServidor(){  }


    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public JsonArray getData() {
        return  this.data;
    }

    public void setData(JsonArray data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
