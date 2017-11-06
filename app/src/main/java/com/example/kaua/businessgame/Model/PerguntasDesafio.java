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

    private boolean respondida = false;
    private String respostaParticipante;

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

    public void setCdPergunta(String cdPergunta) {
        this.cdPergunta = cdPergunta;
    }

    public void setDsPergunta(String dsPergunta) {
        this.dsPergunta = dsPergunta;
    }

    public void setCdCategoria(String cdCategoria) {
        this.cdCategoria = cdCategoria;
    }

    public void setDsResposta1(String dsResposta1) {
        this.dsResposta1 = dsResposta1;
    }

    public void setDsResposta2(String dsResposta2) {
        this.dsResposta2 = dsResposta2;
    }

    public void setDsResposta3(String dsResposta3) {
        this.dsResposta3 = dsResposta3;
    }

    public void setDsResposta4(String dsResposta4) {
        this.dsResposta4 = dsResposta4;
    }

    public void setCorreta(String correta) {
        this.correta = correta;
    }

    public void setAdd_por(String add_por) {
        this.add_por = add_por;
    }

    public boolean isRespondida() {
        return respondida;
    }

    public void setRespondida(boolean respondida) {
        this.respondida = respondida;
    }

    public String getRespostaParticipante() {
        return respostaParticipante;
    }

    public void setRespostaParticipante(String respostaParticipante) {
        this.respostaParticipante = respostaParticipante;
    }
}
