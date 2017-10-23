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

    public GrupoPergunta(String cdgrupo, String nm_equipe, String criado_por ) {
        this.cdGrupo = cdgrupo;
        this.nm_grupo = nm_equipe;
        this.criado_por = criado_por;
    }

    public String getCdGrupo(){ return  this.cdGrupo; }
    public String getNm_equipe(){ return  this.nm_grupo; }
    public String getCriado_por(){ return  this.criado_por; }



}
