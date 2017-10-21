package com.example.kaua.businessgame.Response;
import com.google.gson.annotations.SerializedName;


/**
 * Created by gabri on 19/10/2017.
 */

public class responseCadastrar {
        @SerializedName("sucess")
        public String sucess;

        @SerializedName("cd_usuario")
        public String cd_usuario;

        @SerializedName("nome")
        public String nome ;

        @SerializedName("sessao")
        public String sessao;

        @SerializedName("message")
        public String message;

        public responseCadastrar() {
        }

        public responseCadastrar(String sucess, String cd_usuario, String nome, String sessao, String message  ) {
            this.sucess = sucess;
            this.cd_usuario = cd_usuario;
            this.nome = nome;
            this.sessao = sessao;
            this.message = message;
        }

    }

