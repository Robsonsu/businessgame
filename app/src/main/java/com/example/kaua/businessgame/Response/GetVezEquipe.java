package com.example.kaua.businessgame.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Robson on 05/11/2017.
 */

public class GetVezEquipe {

    @SerializedName("sucess")
    private String sucess;

    @SerializedName("nm_equipe")
    private String nm_equipe;

    @SerializedName("token_equipe")
    private String token_equipe;

    @SerializedName("ordem_partida")
    private String ordem_partida;

    @SerializedName("message")
    private String message;

    public String getSucess() {
        return sucess;
    }

    public void setSucess(String sucess) {
        this.sucess = sucess;
    }

    public String getNm_equipe(){return this.nm_equipe; };
    public String getToken_equipe(){return this.token_equipe; };
    public String getOrdem_partida(){return this.ordem_partida; };

    public void setNm_equipe(String nm){ nm_equipe = nm;}
    public void setToken_equipe(String tk){ token_equipe = tk;}

    public void setOrdem_partida(String ordem){ ordem_partida = ordem;}


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
