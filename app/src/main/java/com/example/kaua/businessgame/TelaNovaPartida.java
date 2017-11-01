package com.example.kaua.businessgame;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.kaua.businessgame.Response.AcessarPartida;
import com.example.kaua.businessgame.Response.RespostaServidor;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaNovaPartida extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Context context;
    private RadioGroup rgTpAcesso;
    private EditText edtToken;
    private TextInputLayout tilEquipe;
    private EditText edtNmEquipe;
    private Button btnConectar;

    public TelaNovaPartida() {
        // Required empty public constructor
    }

    public static TelaNovaPartida newInstance() {
        return new TelaNovaPartida();
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
        View view = inflater.inflate(R.layout.fragment_tela_nova_partida, container, false);

        setView(view);
        setAcoesView();

        return view;
    }

    public void setView(View v){
        rgTpAcesso = (RadioGroup) v.findViewById(R.id.rgTpAcesso);
        edtToken = (EditText) v.findViewById(R.id.edtTokenPartida);
        tilEquipe = (TextInputLayout) v.findViewById(R.id.tilEquipe);
        edtNmEquipe = (EditText) v.findViewById(R.id.edtNmEquipe);
        btnConectar = (Button) v.findViewById(R.id.btnConectarPartida);
    }

    public void setAcoesView(){
        rgTpAcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rgTpAcesso.getId() == R.id.rbLider){
                    tilEquipe.setVisibility(View.VISIBLE);
                }

                if (rgTpAcesso.getId() == R.id.rbIntegrante){
                    tilEquipe.setVisibility(View.GONE);
                }
            }
        });

        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rgTpAcesso.getId() == R.id.rbLider){
                    conectaLider(
                            cacheAplicativo.getIdConectado(),
                            cacheAplicativo.getTokenpartida(),
                            edtNmEquipe.getText().toString()
                    );
                }

                if (rgTpAcesso.getId() == R.id.rbIntegrante){
                    conectaIntegrante(
                            cacheAplicativo.getIdConectado(),
                            cacheAplicativo.getTokenpartida()
                    );
                }
            }
        });

    }

    public void conectaLider(String id, String token, String nmEquipe) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<RespostaServidor> call = service.conectaLider(id, token, nmEquipe);

        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {
                if (response.isSuccessful()) {
                    try {
                        if (!response.body().isSucess()){
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
            public void onFailure(Call<RespostaServidor> call, Throwable t) {
                Toast.makeText(context, "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void conectaIntegrante(String id, String token) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<RespostaServidor> call = service.conectaJogador(id, token);

        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {
                if (response.isSuccessful()) {
                    try {
                        if (!response.body().isSucess()){
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
}
