package com.example.kaua.businessgame.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gabri on 23/10/2017.
 */

public class ResponseConectaLider {
    @SerializedName("sucess")
    private String sucess;

    @SerializedName("token_equipe")
    private String token_equipe;

    @SerializedName("message")
    private String message;

    public String getSucess() {return this.sucess; }
    public String getToken_equipe() {return this.token_equipe; }
    public String getMessage() {return this.message; }
}
