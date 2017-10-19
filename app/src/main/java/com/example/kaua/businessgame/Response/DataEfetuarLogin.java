package com.example.kaua.businessgame.Response;

/**
 * Created by Robson on 18/10/2017.
 */

public class DataEfetuarLogin {

    private String cd_usuario;
    private String nome;
    private String sessao;

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
}
