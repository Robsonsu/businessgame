package com.example.kaua.businessgame.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gabri on 23/10/2017.
 */

public class AcessarPartida {
    @SerializedName("cd_equipe")
    private String cd_equipe;

    @SerializedName("nm_equipe")
    private String nm_equipe;

    @SerializedName("Lider")
    private String Lider;

    public String getCd_equipe(){return this.cd_equipe; };
    public String getNm_equipe(){return this.nm_equipe; };
    public String getLider(){return this.getLider(); };


}
