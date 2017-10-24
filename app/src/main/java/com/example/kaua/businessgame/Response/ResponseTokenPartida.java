package com.example.kaua.businessgame.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gabri on 23/10/2017.
 */

public class ResponseTokenPartida {
    @SerializedName("sucess")
    private String sucess;

    @SerializedName("token_partida")
    private String token_partida;

    @SerializedName("message")
    private String message;

    public String getSucess() {return this.sucess; }
    public String getToken_partida() {return this.token_partida; }
    public String getMessage() {return this.message; }
}
