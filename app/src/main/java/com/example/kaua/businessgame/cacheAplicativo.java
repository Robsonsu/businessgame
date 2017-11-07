package com.example.kaua.businessgame;

import com.example.kaua.businessgame.Model.PerguntasDesafio;
import com.example.kaua.businessgame.Response.ResponseTokenPartida;
import com.example.kaua.businessgame.Response.responseEfetuarLogin;

import java.util.ArrayList;

/**
 * Created by Robson on 19/10/2017.
 */

public class cacheAplicativo {

    private static responseEfetuarLogin responseEfetuarLogin;
    private static String tokenpartida;
    private static String tokenEquipe;
    private static String tokenLider;
    private static int qtdEquipes;
    private static String idConectado;
    private static String tpAcesso;
    private static String minhaVez;
    private static ArrayList<PerguntasDesafio> perguntasDesafios;

    public static responseEfetuarLogin getResponseEfetuarLogin() {
        return responseEfetuarLogin;
    }

    public static void setResponseEfetuarLogin(responseEfetuarLogin responseEfetuarLogin) {
        cacheAplicativo.responseEfetuarLogin = responseEfetuarLogin;
    }

    public static String getTokenpartida() {
        return tokenpartida;
    }

    public static void setTokenpartida(String tokenpartida) {
        cacheAplicativo.tokenpartida = tokenpartida;
    }

    public static int getQtdEquipes() {
        return qtdEquipes;
    }

    public static void setQtdEquipes(int qtdEquipes) {
        cacheAplicativo.qtdEquipes = qtdEquipes;
    }

    public static String getIdConectado() {
        return idConectado;
    }

    public static void setIdConectado(String idConectado) {
        cacheAplicativo.idConectado = idConectado;
    }

    public static String getTpAcesso() {
        return tpAcesso;
    }

    public static void setTpAcesso(String tpAcesso) {
        cacheAplicativo.tpAcesso = tpAcesso;
    }
    public static String getTokenEquipe() {
        return tokenEquipe;
    }

    public static void setTokenEquipe(String tkEquipe) {
        tokenEquipe = tkEquipe;
    }

    public static String getMinhaVez() {
        return minhaVez;
    }

    public static void setMinhaVez(String vez) {
        minhaVez = vez;
    }

    public static ArrayList<PerguntasDesafio> getPerguntasDesafios() {
        return perguntasDesafios;
    }

    public static void setPerguntasDesafios(ArrayList<PerguntasDesafio> perguntasDesafios) {
        cacheAplicativo.perguntasDesafios = perguntasDesafios;
    }

    public static String getTokenLider() {
        return tokenLider;
    }

    public static void setTokenLider(String tokenLider) {
        cacheAplicativo.tokenLider = tokenLider;
    }
}
