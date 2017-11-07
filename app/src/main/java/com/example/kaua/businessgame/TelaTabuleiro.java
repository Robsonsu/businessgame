package com.example.kaua.businessgame;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaua.businessgame.Model.PerguntasDesafio;
import com.example.kaua.businessgame.Model.PerguntasMateria;
import com.example.kaua.businessgame.Response.GetVezEquipe;
import com.example.kaua.businessgame.Response.getFinalizaPartida;
import com.example.kaua.businessgame.Response.responseEfetuarLogin;
import com.example.kaua.businessgame.Response.setPontos;

import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class TelaTabuleiro extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Context context;

    private ImageView iv_dice1, iv_dice2;
    private TextView tv_dice1, tv_dice2, tv_timer;
    private WebView wv_tabuleiro;
    private LinearLayout llDados;
    private RadioButton rb1, rb2,rb3,rb4, rbEscolhido;
    private CheckBox  cb_pergunta_1_materia, cb_pergunta_2_materia,cb_pergunta_3_materia;
    private int diceSubtrair, diceSomar, Auxiliar, AuxiDado, AuxiliarResposta;
    private String CasaTotal, Escolhido;
    private CountDownTimer countDownTimer;
    private Button bt_Comprar_Sim,bt_Comprar_Nao;
    private Boolean iniciarJogada;
    private String alternativa_0,alternativa_1,alternativa_2,correta_materia;

    private String tokenPartida = cacheAplicativo.getTokenpartida();
   // private String tokenPartida ="26C0A195";
    private String perguntaCorretaMateria, perguntaCorretaDesafio;



    SharedPreferences sharedPreferencedice;
    CacheTool cacheDice = new CacheTool();
    Random r;

    int dice1Point = 0, dice2Point = 0;

    public TelaTabuleiro() {
        // Required empty public constructor
    }

    public static TelaTabuleiro newInstance() {
        return new TelaTabuleiro();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tela_tabuleiro, container, false);

        ((tela_principal) context).toolbar.setVisibility(View.VISIBLE);
        sharedPreferencedice = context.getSharedPreferences("telatabuleiro",MODE_PRIVATE);
        setView(view);

//        if (cacheAplicativo.getTpAcesso().equals("1") && !cacheAplicativo.getTpAcesso().equals(null) ){
//            tv_timer.setVisibility(View.GONE);
//            llDados.setVisibility(View.GONE);
//        }

        return view;
    }

    public void setView(View v){
        wv_tabuleiro = (WebView) v.findViewById(R.id.wv_tabuleiro);
        iv_dice1 = (ImageView) v.findViewById(R.id.dice1);
        iv_dice2 = (ImageView) v.findViewById(R.id.dice2);
        tv_dice2 = (TextView) v.findViewById(R.id.tv_dice2);
        tv_timer = (TextView) v.findViewById(R.id.tvTimer);
        tv_timer.setText("60");

        llDados = (LinearLayout) v.findViewById(R.id.rlDados);
        r = new Random();

        setWebView();

        llDados.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               minhaVez(tokenPartida);
                //checkVez();
                if(true){
                    int dice1Throw = r.nextInt(6) + 1;
                    int dice2Throw = r.nextInt(6) + 1;
                    int diceSoma = dice1Throw + dice2Throw;
                    setImagedice1(dice1Throw);
                    setImagedice2(dice2Throw);

                    if(dice1Throw > dice2Throw){
                        dice1Point++;
                    }else{
                        dice2Point++;
                    }

                    tv_dice2.setText(getString(R.string.valor, String.valueOf(diceSoma)));

                    Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate);
                    iv_dice1.startAnimation(rotate);
                    iv_dice2.startAnimation(rotate);
                    mostrarPerguntaSomarSubtrair(dice1Throw,dice2Throw);
                    llDados.setEnabled(false);
                } else{
                    Toast.makeText(context, "Ainda não é sua vez", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void setWebView(){
        ServiceGenerator lurl = new ServiceGenerator();
        String url =lurl.getUrl() + "/tcc/tabuleiro?token_partida=" + tokenPartida;
        //String url = "http://3.bp.blogspot.com/-UMYjDIJ13kY/T0-VjDIxWbI/AAAAAAAAAV4/CnKed9Fhn-g/s1600/jogo+do+resto.jpg";


        wv_tabuleiro.setWebViewClient(new TelaTabuleiro.MyWebViewClient(context));
        wv_tabuleiro.getSettings().setJavaScriptEnabled(true);
        wv_tabuleiro.loadUrl(url); // load the url on the web view

//        WebSettings webSettings = wv_tabuleiro.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        wv_tabuleiro.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {
        ProgressDialog pd;

        public MyWebViewClient(Context context) {
            pd = ProgressDialog.show(context, "", "Aguarde, carregando...", true);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url); // load the url
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            pd.dismiss();
        }
    }

    public void setImagedice1(int num){
        switch (num){
            case 1:
                iv_dice1.setImageResource(R.drawable.dice1);
                break;
            case 2:
                iv_dice1.setImageResource(R.drawable.dice2);
                break;
            case 3:
                iv_dice1.setImageResource(R.drawable.dice3);
                break;
            case 4:
                iv_dice1.setImageResource(R.drawable.dice4);
                break;
            case 5:
                iv_dice1.setImageResource(R.drawable.dice5);
                break;
            case 6:
                iv_dice1.setImageResource(R.drawable.dice6);
                break;
        }
    }
    public void setImagedice2(int num){
        switch (num){
            case 1:
                iv_dice2.setImageResource(R.drawable.dice1);
                break;
            case 2:
                iv_dice2.setImageResource(R.drawable.dice2);
                break;
            case 3:
                iv_dice2.setImageResource(R.drawable.dice3);
                break;
            case 4:
                iv_dice2.setImageResource(R.drawable.dice4);
                break;
            case 5:
                iv_dice2.setImageResource(R.drawable.dice5);
                break;
            case 6:
                iv_dice2.setImageResource(R.drawable.dice6);
                break;
        }
    }

    public void mostrarPerguntaSomarSubtrair(final int dice1,final int dice2){


        sharedPreferencedice = context.getSharedPreferences("telatabuleiro", MODE_PRIVATE);
        CasaTotal = cacheDice.getCache(sharedPreferencedice,"telatabuleiro");
        //if(CasaTotal != ""){Auxiliar =  Integer.parseInt(CasaTotal);}


        diceSubtrair = dice1 - dice2;
        diceSomar = dice1 + dice2;

        if(diceSubtrair < 0){  diceSubtrair = diceSubtrair * (-1);     }
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setCancelable(true);
        builder1
                // .setMessage("Seus dados foram "+ dice1+ " e " + dice2 +", gostaria de somar ou subtrair")
                .setMessage("Seus dados foram "+ dice1+ " e " + dice2 +", gostaria de somar ou subtrair?" )
                .setNegativeButton(

                        "SUBTRAIR = " + diceSubtrair,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AuxiDado = AuxiDado + diceSubtrair;
                                cacheDice.setCache(sharedPreferencedice, "pinoCasa", String.valueOf(AuxiDado));
                                dialog.cancel();
                                timer();
                                getPerguntaDesafio(String.valueOf(AuxiDado));
                            }
                        })
                .setPositiveButton(
                        "SOMAR = " + diceSomar,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                AuxiDado = AuxiDado + diceSomar;
                                cacheDice.setCache(sharedPreferencedice, "pinoCasa",  String.valueOf(AuxiDado));
                                dialog.cancel();
                                timer();
                                getPerguntaDesafio(String.valueOf(AuxiDado));
                            }
                        });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void showPerguntaDesafio( String pergunta, String alternativa1, String alternativa2, String alternativa3, String alternativa4, String correta){
        // SystemClock.sleep(2000);
        perguntaCorretaDesafio = correta;

        final Dialog dialog1 = new Dialog(context);
        dialog1.setContentView(R.layout.unica_pergunta);
        dialog1.setTitle("");

        TextView tvPergunta = (TextView)dialog1.findViewById(R.id.tv_pergunta_dialig);
        RadioButton rbPergunta1 = (RadioButton)dialog1.findViewById(R.id.rb_pergunta_1_dialog);
        RadioButton rbPergunta2 = (RadioButton)dialog1.findViewById(R.id.rb_pergunta_2_dialog);
        RadioButton rbPergunta3 = (RadioButton)dialog1.findViewById(R.id.rb_pergunta_3_dialog);
        RadioButton rbPergunta4 = (RadioButton)dialog1.findViewById(R.id.rb_pergunta_4_dialog);
        final RadioGroup rbgPergunta = (RadioGroup)dialog1.findViewById(R.id.rbg_pergunta_dialog);


        tvPergunta.setText(pergunta);
        rbPergunta1.setText(alternativa1);
        rbPergunta2.setText(alternativa2);
        rbPergunta3.setText(alternativa3);
        rbPergunta4.setText(alternativa4);

        Button button = (Button)dialog1.findViewById(R.id.bt_pergunta);
        Button buttonPasar = (Button)dialog1.findViewById(R.id.bt_Passar);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.cancel();
                showSimOUNao();
            }
        });

        buttonPasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.cancel();
                tv_timer.setText("60");
                countDownTimer.cancel();
                RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
                Call<getFinalizaPartida> call = service.getInfoPartida(tokenPartida,
                        cacheAplicativo.getTokenEquipe());
                Toast.makeText(context, "Você passou a vez", Toast.LENGTH_SHORT).show();

            }
        });

        rbgPergunta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rb_pergunta_1_dialog:
                        Escolhido = "1";
                        break;
                    case R.id.rb_pergunta_2_dialog:
                        Escolhido = "2";
                        break;
                    case R.id.rb_pergunta_3_dialog:
                        Escolhido = "3";
                        break;
                    case R.id.rb_pergunta_4_dialog:
                        Escolhido = "4";
                        break;
                }
            }
        });
        dialog1.show();
    }

    public void showSimOUNao(){
        // int selectedId = rbgPergunta.getCheckedRadioButtonId();
        // rbEscolhido = (RadioButton)dialog.findViewById(selectedId);

        //getPerguntaEstaCorreta?
        if(perguntaCorretaDesafio.equals(Escolhido) ){
            getPerguntaMateria(tokenPartida,String.valueOf(AuxiDado));
            Toast.makeText(context, "Resposta certa", Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(context, "Resposta errada -100 $", Toast.LENGTH_SHORT).show();

            RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
            Call<setPontos> call = service.getSetPontos(false,
                    cacheAplicativo.getTokenEquipe(),"0");
        }
//        else{
//            final Dialog dialog = new Dialog(context);
//            dialog.setContentView(R.layout.comprar_pergunta);
//            dialog.setTitle("");
//            bt_Comprar_Sim = (Button)dialog.findViewById(R.id.btComprar1);
//            bt_Comprar_Nao = (Button)dialog.findViewById(R.id.btComprar2);
//
//            bt_Comprar_Sim.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    dialog.cancel();
//                    getPerguntaMateria(tokenPartida,String.valueOf(AuxiDado));
//                }
//            });
//
//            bt_Comprar_Nao.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
//
//                    Call<setPontos> call = service.getSetPontos(false,
//                            cacheAplicativo.getTokenEquipe(),"0");
//                    dialog.cancel();
//                }
//            });
//
//            dialog.show();
//        }
        countDownTimer.cancel();

    }
    public void showPerguntaMateria(String alternativa0, String alternativa1, String alternativa2, String correta){
        alternativa_0=alternativa0; alternativa_1=alternativa1;  alternativa_2 = alternativa2;  correta_materia = correta;
        perguntaCorretaMateria = correta;
        AuxiliarResposta = 0;
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.unica_pergunta_materia);
        dialog.setTitle("");

        TextView tvPerguntaMateria = (TextView)dialog.findViewById(R.id.tv_pergunta_materia);
        CheckBox cbPergunta1 = (CheckBox)dialog.findViewById(R.id.cb_1);
        CheckBox cbPergunta2 = (CheckBox)dialog.findViewById(R.id.cb_2);
        CheckBox cbPergunta3 = (CheckBox)dialog.findViewById(R.id.cb_3);
        //RadioButton rbPergunta4 = (RadioButton)dialog.findViewById(R.id.rb_pergunta_4_dialog);



        cbPergunta1.setText("(1) " +alternativa0);
        cbPergunta2.setText("(2) " +alternativa1);
        cbPergunta3.setText("(4) " +alternativa2);

        Button button = (Button)dialog.findViewById(R.id.bt_pergunta_materia);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cb_pergunta_1_materia = (CheckBox)dialog.findViewById(R.id.cb_1);
                cb_pergunta_2_materia = (CheckBox)dialog.findViewById(R.id.cb_2);
                cb_pergunta_3_materia = (CheckBox)dialog.findViewById(R.id.cb_3);

                if(cb_pergunta_1_materia.isChecked()){
                    AuxiliarResposta+= 1;
                }
                if(cb_pergunta_2_materia.isChecked()){
                    AuxiliarResposta+= 2;

                }
                if(cb_pergunta_3_materia.isChecked()){
                    AuxiliarResposta+= 4;

                }
                if(perguntaCorretaMateria.equals(String.valueOf(AuxiliarResposta)) ){

                    RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

                    Call<setPontos> call = service.getSetPontos(true,
                            cacheAplicativo.getTokenEquipe(),String.valueOf(AuxiDado));

                    call.enqueue(new Callback<setPontos>() {
                        @Override
                        public void onResponse(Call<setPontos> call, Response<setPontos> response) {
                            if (response.isSuccessful()) {

                                    if (response.body().isSucess()){
                                        Toast.makeText(context, "Resposta Certa", Toast.LENGTH_SHORT).show();

//                                        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
//                                        Call<getFinalizaPartida> call1 = service.getInfoPartida( tokenPartida,
//                                                cacheAplicativo.getTokenEquipe());

                                    } else {
                                        Toast.makeText(context, "Erro no envio da resposta, Favora enviar de novo", Toast.LENGTH_SHORT).show();
                                        showPerguntaMateria( alternativa_0, alternativa_1,  alternativa_2,  correta_materia);
                                    }

                            } else {
                                Toast.makeText(context, "Erro: ".concat(response.body().getMessage()), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<setPontos> call, Throwable t) {
                            Toast.makeText(context, "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //  getSetPontos
                }else{
                    RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
                    Call<getFinalizaPartida> call = service.getInfoPartida(tokenPartida,
                            cacheAplicativo.getTokenEquipe());
                    Toast.makeText(context, "Resposta errada", Toast.LENGTH_SHORT).show();

                }
                tv_timer.setText("60");
                countDownTimer.cancel();
                dialog.cancel();
                //countDownTimer.cancel();

            }
        });

        dialog.show();

    }


    private void timer(){
        countDownTimer = new CountDownTimer(60 * 1000,1000) {
            @Override
            public void onTick(long l) {
                tv_timer.setText("" + (l/1000));
            }

            @Override
            public void onFinish() {
                llDados.setEnabled(true);
                tv_timer.setText("00");
            }
        }.start();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void getPergunta(){


    }

    public void getPerguntaMateria(String token, String dado){
        final ProgressDialog dialog;
        dialog = new ProgressDialog(context); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Carregando. Aguarde...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<PerguntasMateria> call = service.getPergunta(cacheAplicativo.getTokenLider(), dado);

        call.enqueue(new Callback<PerguntasMateria>() {
            @Override
            public void onResponse(Call<PerguntasMateria> call, Response<PerguntasMateria> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.isSuccessful()) {
                        if (response.body().isSucess()){
                            showPerguntaMateria(response.body().getQuestao1(), response.body().getQuestao2(),
                                    response.body().getQuestao3(), response.body().getSomaresultado());
                        } else {
                        }

                } else {
                    Toast.makeText(context, "Erro: ".concat(response.body().getMessage()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PerguntasMateria> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(context, "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void minhaVez(String token){
        iniciarJogada = false;

        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<GetVezEquipe> call = service.getVezJogada(token);

        call.enqueue(new Callback<GetVezEquipe>() {
            @Override
            public void onResponse(Call<GetVezEquipe> call, Response<GetVezEquipe> response) {
                try {
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getNm_equipe().equals(tokenPartida)){
                           iniciarJogada = true;
                        } else {
                            iniciarJogada = false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Erro: ".concat(response.body().getMessage()), Toast.LENGTH_SHORT).show();
                }
            }
             catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<GetVezEquipe> call, Throwable t) {
                Toast.makeText(context, "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPerguntaDesafio(String valor_dado){
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<PerguntasDesafio> call = service.getByPerguntaDesafio(cacheAplicativo.getTokenpartida(),valor_dado);

        call.enqueue(new Callback<PerguntasDesafio>() {
            @Override
            public void onResponse(Call<PerguntasDesafio> call, Response<PerguntasDesafio> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body().isSucess()){
                            showPerguntaDesafio(response.body().getDsPergunta(),response.body().getDsResposta1(),
                                    response.body().getDsResposta2(),response.body().getDsResposta3(),
                                    response.body().getDsResposta4(),response.body().getCorreta() );
                        } else {
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Erro: ".concat(response.body().getMessage()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PerguntasDesafio> call, Throwable t) {
                Toast.makeText(context, "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }
}


