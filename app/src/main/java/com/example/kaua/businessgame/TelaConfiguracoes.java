package com.example.kaua.businessgame;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.kaua.businessgame.Model.GrupoPergunta;
import com.example.kaua.businessgame.Response.ResponseTokenPartida;
import com.example.kaua.businessgame.Response.RespostaServidor;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaConfiguracoes extends Fragment {

    private OnFragmentInteractionListener mListener;
//    private RecyclerView rvListaGrupos;
    private Spinner spnQtdGrupos;
    private Spinner spnGrupoPerguntas;
    final List<GrupoPergunta> listGrupo = new ArrayList<GrupoPergunta>();
    private Button btnAvancar;

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

        ((tela_principal) context).toolbar.setVisibility(View.VISIBLE);
        setView(view);
        setAcoesView();

        // Inflate the layout for this fragment
        return view;
    }

    public void setView(View v){
        spnQtdGrupos = (Spinner) v.findViewById(R.id.spnQtdEquipes);
        spnGrupoPerguntas = (Spinner) v.findViewById(R.id.spnGrupoPerguntas);
        btnAvancar = (Button) v.findViewById(R.id.btnIniciarConfig);

        llBtn = (LinearLayout) v.findViewById(R.id.llBtnConfig);

        String[] qt_equipes = new String[]{"2 ", "3 ", "4 ", "5"};
        try {
          CarregaGruposPerguntas();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> arrayEquipe = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, qt_equipes);
        arrayEquipe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnQtdGrupos.setAdapter(arrayEquipe);
    }

    public void setAcoesView(){
        btnAvancar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int qt = spnQtdGrupos.getSelectedItemPosition()+2;
                int itemPosition =  spnGrupoPerguntas.getSelectedItemPosition();
                cacheAplicativo.setQtdEquipes(spnQtdGrupos.getSelectedItemPosition() + 2);

                IniciarPartida(String.valueOf(qt), String.valueOf(itemPosition));

                //if's the first time created
//                Fragment tela_token = new TelaToken();
//                Fragment config = TelaConfiguracoes.this ;
//                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
//                fragmentTransaction.remove(config);
//                fragmentTransaction.replace( android.R.id.content, tela_token, "TelaToken");
//                fragmentTransaction.commit();
            }
        });
    }

    //PEGAR O TOKEN E MOSTRAR NA TELA
    public void IniciarPartida(String qt_grupo, String cd_grupo) {
        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<ResponseTokenPartida> call = service.novaPartida(cacheAplicativo.getResponseEfetuarLogin().getCd_usuario(), qt_grupo, cd_grupo);

        call.enqueue(new Callback<ResponseTokenPartida>() {
            @Override
            public void onResponse(Call<ResponseTokenPartida> call, Response<ResponseTokenPartida> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getSucess().equals("true")) {
                            String tk = response.body().getToken_partida();
                            cacheAplicativo.setTokenpartida(tk);

                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fl_principal, new TelaToken());
                            fragmentTransaction.commit();
                        } else {
                            System.out.println(response.body().getMessage());
                            //MOSTRA ERRO NA TELA
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ResponseTokenPartida> call, Throwable t) {
                Toast.makeText(context, "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
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
                if (response.isSuccessful()) {
                    if (response.body().isSucess()) {
                        Gson gson = new Gson();
                        JsonArray json = response.body().getData();
                        String[] nm_grupos = new String[json.size()];
                        int i = 0;
                        for (JsonElement j : json) {
                            GrupoPergunta gp = gson.fromJson(j.toString(), GrupoPergunta.class);
                            nm_grupos[i] = gp.getNm_grupo();
                            i++;
                            // listGrupo.add(gp);
                        }

                        ArrayAdapter<String> adapterGrupo = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, nm_grupos);
                        adapterGrupo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnGrupoPerguntas.setAdapter(adapterGrupo);
                    } else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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
