package com.example.kaua.businessgame.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gabri on 23/10/2017.
 */

public class PerguntasDesafio {
    @SerializedName("cdPergunta")
    private String cdPergunta;

    @SerializedName("dsPergunta")
    private String dsPergunta;

    @SerializedName("cdCategoria")
    private String cdCategoria;

    @SerializedName("dsResposta1")
    private String dsResposta1;

    @SerializedName("dsResposta2")
    private String dsResposta2;

    @SerializedName("dsResposta3")
    private String dsResposta3;

    @SerializedName("dsResposta4")
    private String dsResposta4;

    @SerializedName("correta")
    private String correta;

    @SerializedName("add_por")
    private String add_por;

    public  PerguntasDesafio()
    {

    }

    public String getCdPergunta(){return this.cdPergunta; };
    public String getCdCategoria(){return this.cdCategoria; };
    public String getDsPergunta(){return this.dsPergunta; };
    public String getDsResposta1(){return this.dsResposta1; };
    public String getDsResposta2(){return this.dsResposta2; };
    public String getDsResposta3(){return this.dsResposta3; };
    public String getDsResposta4(){return this.dsResposta4; };
    public String getCorreta(){return this.correta; };
    public String getAdd_por(){return this.add_por; };

}
