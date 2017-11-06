package com.example.kaua.businessgame.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gabri on 23/10/2017.
 */

public class PerguntasMateria {

    @SerializedName("sucess")
    private boolean sucess;

    @SerializedName("cd_pergunta")
    private String cd_pergunta;

    @SerializedName("questao1")
    private String questao1;

    @SerializedName("questao2")
    private String questao2;

    @SerializedName("questao3")
    private String questao3;

    @SerializedName("somaresultado")
    private String somaresultado;

    @SerializedName("cd_categoria")
    private String cd_categoria;

    @SerializedName("add_por")
    private String add_por;

    @SerializedName("message")
    private String message;

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getCd_pergunta(){ return  this.cd_pergunta; }
    public String getQuestao1(){ return  this.questao1; }
    public String getQuestao2(){ return  this.questao2; }
    public String getQuestao3(){ return  this.questao3; }
    public String getSomaresultado(){ return  this.somaresultado; }
    public String getCd_categoria(){ return  this.cd_categoria; }
    public String getAdd_por(){ return  this.add_por; }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
