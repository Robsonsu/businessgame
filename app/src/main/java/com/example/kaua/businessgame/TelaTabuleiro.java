package com.example.kaua.businessgame;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class TelaTabuleiro extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Context context;

    private ImageView iv_dice1, iv_dice2;
    private TextView tv_dice1, tv_dice2, tv_timer;
    private WebView wv_tabuleiro;
    private LinearLayout llDados;
    private RadioButton rb1, rb2,rb3,rb4, rbEscolhido;
    private int diceSubtrair, diceSomar, Auxiliar;
    private String CasaTotal, Escolhido;
    private CountDownTimer countDownTimer;

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
//        tv_dice1 = (TextView) v.findViewById(R.id.tv_dice1);
        tv_dice2 = (TextView) v.findViewById(R.id.tv_dice2);
        tv_timer = (TextView) v.findViewById(R.id.tvTimer);
        tv_timer.setText("60");

        llDados = (LinearLayout) v.findViewById(R.id.rlDados);
        r = new Random();

        setWebView("");

        llDados.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

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

                // tv_dice1.setText("DICE1: " + dice1Point);
                tv_dice2.setText(getString(R.string.valor, String.valueOf(diceSoma)));

                Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate);
                iv_dice1.startAnimation(rotate);
                iv_dice2.startAnimation(rotate);
                mostrarPerguntaSomarSubtrair(dice1Throw,dice2Throw);
                llDados.setEnabled(false);
            }
        });
    }

    public void setWebView(String token){
        ServiceGenerator lurl = new ServiceGenerator();
        String url =lurl.getUrl() + "/tcc/tabuleiro?token_partida=" + cacheAplicativo.getTokenpartida();
        //String url = "http://3.bp.blogspot.com/-UMYjDIJ13kY/T0-VjDIxWbI/AAAAAAAAAV4/CnKed9Fhn-g/s1600/jogo+do+resto.jpg";

        // set web view client
        wv_tabuleiro.setWebViewClient(new TelaTabuleiro.MyWebViewClient());
        wv_tabuleiro.getSettings().setJavaScriptEnabled(true);
        wv_tabuleiro.loadUrl(url); // load the url on the web view

//        WebSettings webSettings = wv_tabuleiro.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        wv_tabuleiro.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url); // load the url
            return true;
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
                                Auxiliar = Auxiliar + diceSubtrair;
                                cacheDice.setCache(sharedPreferencedice, "pinoCasa", Integer.toString(Auxiliar));
                                dialog.cancel();
                                timer();
                                showPergunta();
                            }
                        })
                .setPositiveButton(
                        "SOMAR = " + diceSomar,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Auxiliar = Auxiliar + diceSomar;
                                cacheDice.setCache(sharedPreferencedice, "pinoCasa",  Integer.toString(Auxiliar));
                                dialog.cancel();
                                timer();
                                showPergunta();
                            }
                        });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void showPergunta(){
        // SystemClock.sleep(2000);

        final String closeDialog = "";

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.unica_pergunta);
        dialog.setTitle("");

        TextView tvPergunta = (TextView)dialog.findViewById(R.id.tv_pergunta_dialig);
        RadioButton rbPergunta1 = (RadioButton)dialog.findViewById(R.id.rb_pergunta_1_dialog);
        RadioButton rbPergunta2 = (RadioButton)dialog.findViewById(R.id.rb_pergunta_2_dialog);
        RadioButton rbPergunta3 = (RadioButton)dialog.findViewById(R.id.rb_pergunta_3_dialog);
        RadioButton rbPergunta4 = (RadioButton)dialog.findViewById(R.id.rb_pergunta_4_dialog);
        final RadioGroup rbgPergunta = (RadioGroup)dialog.findViewById(R.id.rbg_pergunta_dialog);


        tvPergunta.setText("PERGUNTA AQUI ESTAMOS BLA BLA BLA BLA BLA ?" +
                "PERGUNTA AQUI ESTAMOS BLA BLA BLA BLA BLA ?" +
                "PERGUNTA AQUI ESTAMOS BLA BLA BLA BLA BLA ?" +
                "PERGUNTA AQUI ESTAMOS BLA BLA BLA BLA BLA ?" +
                "");

        rbPergunta1.setText("ROBSON BLA BLA BLA BLA BLA BLA BDSASDJAJSDOAJSDASDJASODJASO");
        rbPergunta2.setText("de");
        rbPergunta3.setText("Oliveira");
        rbPergunta4.setText("Su");

        Button button = (Button)dialog.findViewById(R.id.bt_pergunta);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = rbgPergunta.getCheckedRadioButtonId();
//                rbEscolhido = (RadioButton) findViewById(selectedId);
                dialog.cancel();
                countDownTimer.cancel();

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
}
