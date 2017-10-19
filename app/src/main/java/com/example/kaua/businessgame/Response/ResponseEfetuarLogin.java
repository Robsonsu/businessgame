package com.example.kaua.businessgame.Response;


/**
 * Created by Kauã on 10/09/2017.
 */

public class ResponseEfetuarLogin {


    private String sucess;
    private String cd_usuario;
    private String nome;
    private String sessao;
    private String message;

    public String getCd_usuario() {
        return cd_usuario;
    }

    public void setCd_usuario(String cd_usuario) {
        this.cd_usuario = cd_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }


    public ResponseEfetuarLogin(){}


    public String isSucess() {
        return sucess;
    }

    public void setSucess(String sucess) {
        this.sucess = sucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
