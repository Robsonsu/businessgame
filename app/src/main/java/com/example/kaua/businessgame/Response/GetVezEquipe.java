package com.example.kaua.businessgame.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Robson on 05/11/2017.
 */

public class GetVezEquipe {

    @SerializedName("nm_equipe")
    private String nm_equipe;

    @SerializedName("token_equipe")
    private String token_equipe;

    @SerializedName("ordem_partida")
    private String ordem_partida;

    public String getNm_equipe(){return this.nm_equipe; };
    public String getToken_equipe(){return this.token_equipe; };
    public String getOrdem_partida(){return this.ordem_partida; };

}
