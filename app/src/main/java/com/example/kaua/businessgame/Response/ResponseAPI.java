package com.example.kaua.businessgame.Response;
import com.google.gson.annotations.SerializedName;



/**
 * Created by gabri on 19/10/2017.
 */

public class ResponseAPI {
    @SerializedName("sucess")
    private String sucess;

    @SerializedName("cd_usuario")
    private String cd_usuario;

    @SerializedName("nome")
    private String nome;

    @SerializedName("cd_tipousuario")
    private String cd_tipousuario ;

    @SerializedName("sessao")
    private String sessao;

    @SerializedName("message")
    private String message;

    public ResponseAPI() {
    }

    public ResponseAPI(String sucess, String cd_usuario, String nome, String sessao, String message  ) {
        this.sucess = sucess;
        this.cd_usuario = cd_usuario;
        this.nome = nome;
        this.sessao = sessao;
        this.message = message;
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

    public String getCd_tipousuario() {
        return cd_tipousuario;
    }
}

