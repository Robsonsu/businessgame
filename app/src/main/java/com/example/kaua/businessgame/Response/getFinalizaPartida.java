package com.example.kaua.businessgame.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Robson on 05/11/2017.
 */

public class getFinalizaPartida {

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

    @SerializedName("co_lider")
    private String co_lider;

    @SerializedName("ordem_partida")
    private String ordem_partida;

    @SerializedName("minha_vez")
    private String minha_vez;

    public String getCd_equipe(){return this.cd_equipe; };
    public String getNm_equipe(){return this.nm_equipe; };
    public String getPontos(){return this.pontos; };
    public String getPos_tabuleiro(){return this.pos_tabuleiro; };
    public String getToken_partida(){return this.token_partida; };
    public String getToken_equipe(){return this.token_equipe; };
    public String getCo_lider(){return this.co_lider; };
    public String getOrdem_partida(){return this.ordem_partida; };
    public String getMinha_vez(){return this.minha_vez; };
}
