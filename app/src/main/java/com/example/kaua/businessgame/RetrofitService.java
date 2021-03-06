package com.example.kaua.businessgame;

import com.example.kaua.businessgame.Model.PerguntasDesafio;
import com.example.kaua.businessgame.Model.PerguntasMateria;
import com.example.kaua.businessgame.Response.AcessarPartida;
import com.example.kaua.businessgame.Response.GetVezEquipe;
import com.example.kaua.businessgame.Response.ResponseConectaLider;
import com.example.kaua.businessgame.Response.ResponseTokenPartida;
import com.example.kaua.businessgame.Response.responseEfetuarLogin;
import com.example.kaua.businessgame.Response.RespostaServidor;
import com.example.kaua.businessgame.Response.getFinalizaPartida;
import com.example.kaua.businessgame.Response.setPontos;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Kauã on 10/09/2017.
 */

public interface RetrofitService {

//    @Headers("X-Mashape-Key: AuuyclCPjcmshv2iOPq190OpzLrMp1FJWwejsnJrdfwOUr4h44")

    @POST("efetuarlogin")
    Call<responseEfetuarLogin> efetuarlogin(@Body RequestBody requestBody);

    @POST("encerrarSessao")
    Call<RespostaServidor> encerrarSessao(@Body RequestBody requestBody);

    @POST("usuário/cadastrar")
    Call<RespostaServidor> cadastrar(@Body RequestBody requestBody);

    @GET("partida/nova")
    Call<ResponseTokenPartida> novaPartida(@Query("id") String id,
                                           @Query("qt") String qt,
                                           @Query("grupo") String grupo);

    @GET("partida/conectaLider")
    Call<ResponseConectaLider> conectaLider(@Query("id") String id,
                                            @Query("tokenPartida") String tokenPartida,
                                            @Query("nmEquipe") String nmEquipe);

    @GET("partida/conectaJogador")
    Call<ResponseTokenPartida> conectaJogador(@Query("id") String id,
                                        @Query("token") String token);

    @GET("partida/acessar")
    Call<RespostaServidor> acessarPartida(@Query("id") String id,
                                        @Query("token") String token);

    @GET("partida/encerrar")
    Call<RespostaServidor> encerrarPartida(@Query("token_partida") String tokenPartida);

    @GET("perguntasMateria/getall")
    Call<RespostaServidor> getAllMateria();

    @GET("perguntasMateria/getByID")
    Call<RespostaServidor> getByIDMateria(@Query("id") String id);

    @GET("perguntasMateria/getByUser")
    Call<RespostaServidor> getByUserMateria(@Query("id") String id);

    @GET("perguntasdesafio/getall")
    Call<RespostaServidor> getAllDesafio();

    @GET("perguntasdesafio/getByID")
    Call<RespostaServidor> getByIDDesafio(@Query("id") String id);


    @GET("perguntasdesafio/getByTabuleiro")
    Call<PerguntasDesafio> getByPerguntaDesafio(@Query("token_equipe") String token_equipe,
                                                @Query("dado") String dado);

    @GET("perguntasdesafio/getByUser")
    Call<RespostaServidor> getByUserDesafio(@Query("id") String id);

    @GET("perguntasdesafio/getPaginacao")
    Call<RespostaServidor> getPaginacaoDesafio(@Query("page") String page);

    @GET("perguntasgrupo/add")
    Call<RespostaServidor> addPerguntaGrupo(@Query("cdgrupo") String cdgrupo,
                                         @Query("cdpergunta") String cdpergunta);

    @GET("perguntasgrupo/get")
    Call<RespostaServidor> getPerguntaGrupo(@Query("id") String id);

    @GET("perguntasgrupo/getByPergunta")
    Call<RespostaServidor> getPorPerguntaGrupo(@Query("id") String id);

    @GET("perguntasgrupo/remove")
    Call<RespostaServidor> removePerguntaGrupo(@Query("id") String id);

    @GET("grupo/getall")
    Call<RespostaServidor> getAllGrupo();

    @GET("grupo/getByID")
    Call<RespostaServidor> getByIDGrupo(@Query("id") String id);

    @GET("grupo/getByUser")
    Call<RespostaServidor> getByUserGrupo(@Query("id") String id);

    @GET("perguntasmateria/getbytabuleiro")
    Call<PerguntasMateria> getPergunta(@Query("token_equipe") String tokenEquipe,
                                       @Query("dado") String dado);

    @GET("partida/finalizarjogada")
    Call<getFinalizaPartida> getInfoPartida(@Query("token_partida") String tokenPartida,
                                            @Query("token_equipe") String tokenEquipe);

    @GET("partida/getvezequipe")
    Call<GetVezEquipe> getVezJogada(@Query("token_partida") String tokenPartida);

    @GET("partida/setPontos")
    Call<setPontos> getSetPontos(@Query("acertou") String acertou,
                                 @Query("token_equipe") String tokenEquipe,
                                 @Query("casas") String casas);
}