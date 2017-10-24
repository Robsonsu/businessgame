package com.example.kaua.businessgame;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;


import com.example.kaua.businessgame.Model.GrupoPergunta;
import com.example.kaua.businessgame.Response.AcessarPartida;
import com.example.kaua.businessgame.Response.ResponseTokenPartida;
import com.example.kaua.businessgame.Response.RespostaServidor;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaConfiguracoes extends Fragment {

    private OnFragmentInteractionListener mListener;
//    private RecyclerView rvListaGrupos;
    private Spinner spnQtdGrupos;
    private Spinner spnGrupoPerguntas;

    private LinearLayout llBtn;
    private Context context;
   // private ArrayList<Grupo> grupos = new ArrayList<Grupo>();
//    private Button btnOkGrupos;

    public TelaConfiguracoes() {
        // Required empty public constructor
    }

    public static TelaConfiguracoes newInstance() {
        return new TelaConfiguracoes();
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
        View view = inflater.inflate(R.layout.fragment_tela_configuracoes, container, false);

        setView(view);
       Button  btnAvancar = (Button) view.findViewById(R.id.btnIniciarConfig);

        btnAvancar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                IniciarPartida(view);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void setView(View v){
        spnQtdGrupos = (Spinner) v.findViewById(R.id.spnQtdEquipes);
        spnGrupoPerguntas = (Spinner) v.findViewById(R.id.spnGrupoPerguntas);

        llBtn = (LinearLayout) v.findViewById(R.id.llBtnConfig);

        String[] qt_equipes = new String[]{"1 ", "2 ", "3 ", "4 ", "5"};
        try {
          CarregaGruposPerguntas();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> arrayEquipe = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, qt_equipes);
        arrayEquipe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnQtdGrupos.setAdapter(arrayEquipe);



    }
    //PEGAR O TOKEN E MOSTRAR NA TELA
    public void IniciarPartida(final View view)
    {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<ResponseTokenPartida> call = service.novaPartida(cacheAplicativo.getResponseEfetuarLogin().getCd_usuario(), "2", "2");

        call.enqueue(new Callback<ResponseTokenPartida>() {
            @Override
            public void onResponse(Call<ResponseTokenPartida> call, Response<ResponseTokenPartida> response) {
                try {
                  response.body();
                    ResponseTokenPartida tokenPartida = new ResponseTokenPartida();
                    tokenPartida = response.body();

                    Bundle bundle = new Bundle();

                    if (tokenPartida.getSucess().equals("true")) {
                        bundle.putString("fragment", "tela_token");
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseTokenPartida> call, Throwable t) {
            }
        });

    }

//CARREGAR O GRUPOS NA TELA
    public void CarregaGruposPerguntas() throws IOException {

        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<RespostaServidor> call = service.getByUserGrupo(cacheAplicativo.getResponseEfetuarLogin().getCd_usuario());

        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {
                Gson gson = new Gson();
                JsonArray json = response.body().getData();
                //for (JsonElement j: json) {
              //      GrupoPergunta gp =  gson.fromJson(j.toString(), GrupoPergunta.class);
              //  }
            }

            @Override
            public void onFailure(Call<RespostaServidor> call, Throwable t) {
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
