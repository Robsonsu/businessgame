package com.example.kaua.businessgame.Request;

/**
 * Created by Kauã on 14/10/2017.
 */

public class Cadastrar {

    private String nome;
    private String senha;
    private String tpAcesso;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTpAcesso() {
        return tpAcesso;
    }

    public void setTpAcesso(String tpAcesso) {
        this.tpAcesso = tpAcesso;
    }
}
