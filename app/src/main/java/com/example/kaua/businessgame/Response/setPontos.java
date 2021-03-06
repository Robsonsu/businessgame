package com.example.kaua.businessgame.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Robson on 06/11/2017.
 */

public class setPontos {
    @SerializedName("sucess")
    private boolean sucess;

    @SerializedName("cd_equipe")
    private String cd_equipe;

    @SerializedName("nm_equipe")
    private String nm_equipe;

    @SerializedName("pontos")
    private String pontos;

    @SerializedName("pos_tabuleiro")
    private String pos_tabuleiro;

    @SerializedName("token_partida")
    private String token_partida;

    @SerializedName("token_equipe")
    private String token_equipe;

    @SerializedName("cd_lider")
    private String cd_lider;

    @SerializedName("ordem_partida")
    private String ordem_partida;

    @SerializedName("minha_vez")
    private String minha_vez;

    @SerializedName("message")
    private String message;

    public setPontos(){}

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getCd_equipe(){return this.cd_equipe; };
    public String getNm_equipe(){return this.nm_equipe; };
    public String getPontos(){return this.pontos; };
    public String getPos_tabuleiro(){return this.pos_tabuleiro; };
    public String getToken_partida(){return this.token_partida; };
    public String getToken_equipe(){return this.token_equipe; };
    public String getCd_lider(){return this.cd_lider; };
    public String getOrdem_partida(){return this.ordem_partida; };
    public String getMinha_vez(){return this.minha_vez; };
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
