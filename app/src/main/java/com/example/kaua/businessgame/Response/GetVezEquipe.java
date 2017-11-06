package com.example.kaua.businessgame.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Robson on 05/11/2017.
 */

public class GetVezEquipe {

    @SerializedName("sucess")
    private boolean sucess;

    @SerializedName("nm_equipe")
    private String nm_equipe;

    @SerializedName("token_equipe")
    private String token_equipe;

    @SerializedName("ordem_partida")
    private String ordem_partida;

    @SerializedName("message")
    private String message;

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getNm_equipe(){return this.nm_equipe; };
    public String getToken_equipe(){return this.token_equipe; };
    public String getOrdem_partida(){return this.ordem_partida; };

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
