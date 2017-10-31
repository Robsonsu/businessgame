package com.example.kaua.businessgame;

import com.example.kaua.businessgame.Response.ResponseTokenPartida;
import com.example.kaua.businessgame.Response.responseEfetuarLogin;

/**
 * Created by Robson on 19/10/2017.
 */

public class cacheAplicativo {

    private static responseEfetuarLogin responseEfetuarLogin;
    private static String tokenpartida;
    private static int qtdEquipes;
    private static String idConectado;

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
}
