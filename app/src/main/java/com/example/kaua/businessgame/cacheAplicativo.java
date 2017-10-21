package com.example.kaua.businessgame;

import com.example.kaua.businessgame.Response.responseEfetuarLogin;

/**
 * Created by Robson on 19/10/2017.
 */

public class cacheAplicativo {

    private static responseEfetuarLogin responseEfetuarLogin;

    public static responseEfetuarLogin getResponseEfetuarLogin() {
        return responseEfetuarLogin;
    }

    public static void setResponseEfetuarLogin(responseEfetuarLogin responseEfetuarLogin) {
        cacheAplicativo.responseEfetuarLogin = responseEfetuarLogin;
    }
}
