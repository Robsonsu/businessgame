package com.example.kaua.businessgame.Resposta;

/**
 * Created by Robson on 21/10/2017.
 */

public class RespostasDesafio {

    private String cd_pergunta;
    private String resposta_usuario;

    public RespostasDesafio(){}


    public String getPergunta() {
        return cd_pergunta;
    }

    public void setPergunta(String cd_pergunta) {
        this.cd_pergunta = cd_pergunta;
    }


    public String getResposta_usuario() {
        return resposta_usuario;
    }

    public void setResposta_usuario(String resposta_usuario) {
        this.resposta_usuario = resposta_usuario;
    }

}
