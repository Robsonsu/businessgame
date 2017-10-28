package com.example.kaua.businessgame.Model;
import com.google.gson.annotations.SerializedName;


import java.util.List;

/**
 * Created by Kauã on 02/10/2017.
 */

public class GrupoPergunta {

    @SerializedName("cdGrupo")
    private String cdGrupo;

    @SerializedName("nm_grupo")
    private String nm_grupo;

    @SerializedName("criado_por")
    private String criado_por;



    public GrupoPergunta(){};

    public String getCdGrupo(){ return  this.cdGrupo; }
    public String getNm_grupo(){ return  this.nm_grupo; }
    public String getCriado_por(){ return  this.criado_por; }





}
