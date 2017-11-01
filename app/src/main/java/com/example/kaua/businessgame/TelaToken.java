package com.example.kaua.businessgame;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaua.businessgame.Model.GrupoPergunta;
import com.example.kaua.businessgame.Response.AcessarPartida;
import com.example.kaua.businessgame.Response.ResponseTokenPartida;
import com.example.kaua.businessgame.Response.RespostaServidor;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaToken extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ProgressBar pb;
    private TextView tvToken;
    private TextView tvParticipantes;
    private TextView tvEquipes;
    private Button btnCancelar;
    private Button btnAvancar;
    private Context context;
    private LinearLayout llLoadEquipes;

    public TelaToken() {
        // Required empty public constructor
    }
    public static TelaToken newInstance() {
        return new TelaToken();
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
        View view = inflater.inflate(R.layout.fragment_tela_token, container, false);

        setView(view);
        setAcoesViews();

        // Inflate the layout for this fragment
        return view;
    }

    public void setView(View v){
//        pb = (ProgressBar) v.findViewById(R.id.pbToken);
        tvToken = (TextView) v.findViewById(R.id.tvCodToken);
        tvParticipantes = (TextView) v.findViewById(R.id.tvPartConectados);
        btnCancelar = (Button) v.findViewById(R.id.btnCancelarToken);
        btnAvancar = (Button) v.findViewById(R.id.btnAvancarToken);
        tvEquipes = (TextView) v.findViewById(R.id.tvNmEquipes);
        llLoadEquipes = (LinearLayout) v.findViewById(R.id.llLoadEquipes);

        tvToken.setText(cacheAplicativo.getTokenpartida());
        tvParticipantes.setText(getString(R.string.usuarioConectados, "0"));

        btnAvancar.setVisibility(View.GONE);

        Thread td2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    atualizarEquipes();
                }catch (InterruptedException e){}
            }
        });

        td2.start();
    }

    public void setAcoesViews(){
        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, tela_tabuleiro.class));
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encerrarPartida();
            }
        });
    }

    public void verificaEquipes() {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<RespostaServidor> call = service.acessarPartida(cacheAplicativo.getIdConectado(), cacheAplicativo.getTokenpartida());

        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {
                if (response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        JsonArray json = response.body().getData();
                        String[] nm_equipes = new String[json.size()];
                        String[] nm_lider = new String[json.size()];
                        tvParticipantes.setText(getString(R.string.usuarioConectados, String.valueOf(json.size())));
                        int i = 0;
                        for (JsonElement j : json) {
                            AcessarPartida ap = gson.fromJson(j.toString(), AcessarPartida.class);
                            nm_equipes[i] = ap.getNm_equipe();
                            nm_lider[i] = ap.getLider();
                            i++;
                        }

                        String equipeConectadas = "";
                        for (int j = 0; j < i; j++){
                            equipeConectadas +=
                                    "Líder: ".concat(nm_lider[j].concat(
                                            "\nEquipe: ".concat(nm_equipes[j].concat((j+1 != i) ? "\n\n" : ""))));

                        }

                        if (!response.body().isSucess()){
                            tvEquipes.setText(equipeConectadas);

                            try{
                                atualizarEquipes();
                            }catch (InterruptedException e){}
                        } else {
                            llLoadEquipes.setVisibility(View.GONE);
                            tvEquipes.setText(equipeConectadas);
                            tvParticipantes.setText("Todos prontos!");
                            btnAvancar.setVisibility(View.VISIBLE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try{
                        atualizarEquipes();
                    }catch (InterruptedException e){}
//                    Toast.makeText(context, "Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<RespostaServidor> call, Throwable t) {
                try{
                    atualizarEquipes();
                }catch (InterruptedException e){}
//                Toast.makeText(context, "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void atualizarEquipes() throws InterruptedException {
        Thread td = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                verificaEquipes();
            }
        });

        td.start();
    }

    public void encerrarPartida() {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<RespostaServidor> call = service.encerrarPartida(cacheAplicativo.getTokenpartida());

        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {
                if (response.isSuccessful()) {
                    try {
                        if (!response.body().isSucess()){
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fl_principal, new TelaConfiguracoes());
                            fragmentTransaction.commit();
                        } else {
                            Toast.makeText(context, "Erro: ".concat(response.body().getMessage()), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Erro: ".concat(response.body().getMessage()), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<RespostaServidor> call, Throwable t) {
                Toast.makeText(context, "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

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

    public class DelayTask extends AsyncTask<Void, Integer, String> {
        int count = 0;

        @Override
        protected void onPreExecute() {
            pb.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            while (count < 5) {
                SystemClock.sleep(1000);
                count++;
                publishProgress(count * 20);
            }
            return "Complete";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            pb.setProgress(values[0]);
        }
    }
}
