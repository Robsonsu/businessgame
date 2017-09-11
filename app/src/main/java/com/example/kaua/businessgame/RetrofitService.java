package com.example.kaua.businessgame;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Kauã on 10/09/2017.
 */

public interface RetrofitService {

//    @Headers("X-Mashape-Key: AuuyclCPjcmshv2iOPq190OpzLrMp1FJWwejsnJrdfwOUr4h44")

    @POST("efetuarlogin")
    Call<RespostaServidor> efetuarlogin(@Body RequestBody requestBody);
}