package com.example.kaua.businessgame;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaua.businessgame.Response.ResponseTokenPartida;
import com.example.kaua.businessgame.Response.RespostaServidor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaToken extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ProgressBar pb;
    private TextView tvToken;
    private TextView tvParticipantes;
    private Button btnCancelar;
    private Button btnAvancar;
    private Context context;

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

        // Inflate the layout for this fragment
        return view;
    }

    public void setView(View v){
//        pb = (ProgressBar) v.findViewById(R.id.pbToken);
        tvToken = (TextView) v.findViewById(R.id.tvCodToken);
        tvParticipantes = (TextView) v.findViewById(R.id.tvPartConectados);
        btnCancelar = (Button) v.findViewById(R.id.btnCancelarToken);
        btnAvancar = (Button) v.findViewById(R.id.btnAvancarToken);

        tvToken.setText(cacheAplicativo.getTokenpartida());
        tvParticipantes.setText(getString(R.string.usuarioConectados, "0"));

        btnAvancar.setVisibility(View.GONE);
    }

    public void setAcoesViews(){

    }

    public void IniciarPartida(String qt_grupo, String cd_grupo) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<RespostaServidor> call = service.acessarPartida("id", "token");

        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {
                if (response.isSuccessful()) {
                    try {
//                        if (response.body().getSucess().equals("true")) {
//                            String tk = response.body().getToken_partida();
//                            cacheAplicativo.setTokenpartida(tk);
//                            System.out.println("TOKEN " + cacheAplicativo.getTokenpartida());
//                        } else {
//                            System.out.println(response.body().getMessage());
//                            //MOSTRA ERRO NA TELA
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
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
