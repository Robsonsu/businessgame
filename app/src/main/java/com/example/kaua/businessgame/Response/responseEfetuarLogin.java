package com.example.kaua.businessgame.Response;
import com.google.gson.annotations.SerializedName;



/**
 * Created by gabri on 19/10/2017.
 */

public class responseEfetuarLogin {

    @SerializedName("sucess")
    private String sucess;

    @SerializedName("cd_usuario")
    private String cd_usuario;

    @SerializedName("nome")
    private String nome ;

    @SerializedName("sessao")
    private String sessao;

    @SerializedName("message")
    private String message;

    @SerializedName("tp_usuario")
    private String tp_usuario;

    public responseEfetuarLogin() {
        }

        public responseEfetuarLogin(String sucess, String cd_usuario, String nome, String sessao, String message, String tp_usuario) {
            this.sucess = sucess;
            this.cd_usuario = cd_usuario;
            this.nome = nome;
            this.sessao = sessao;
            this.message = message;
            this.tp_usuario = tp_usuario;
        }

    public String getSucess() {
        return sucess;
    }

    public String getCd_usuario() {
        return cd_usuario;
    }

    public String getNome() {
        return nome;
    }

    public String getSessao() {
        return sessao;
    }

    public String getMessage() {
        return message;
    }

    public String getTp_usuario() {
        return tp_usuario;
    }
}

