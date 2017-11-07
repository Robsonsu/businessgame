package com.example.kaua.businessgame;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.kaua.businessgame.Model.GrupoPergunta;
import com.example.kaua.businessgame.Model.PerguntasDesafio;
import com.example.kaua.businessgame.Response.AcessarPartida;
import com.example.kaua.businessgame.Response.RespostaServidor;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaPergunta extends Fragment {

    private Context context;
    private OnFragmentInteractionListener mListener;
    private RecyclerView rvPerguntasDesafio;
    private ArrayList<PerguntasDesafio> perguntasDesafios = new ArrayList<>();

    public TelaPergunta() {
        // Required empty public constructor
    }

    public static TelaPergunta newInstance() {
        return new TelaPergunta();
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
        View view = inflater.inflate(R.layout.fragment_tela_pergunta, container, false);

        ((tela_principal) context).toolbar.setVisibility(View.VISIBLE);
        setView(view);
        carregarPerguntas();

        // Inflate the layout for this fragment
        return view;
    }

    public void setView(View v){
        rvPerguntasDesafio = (RecyclerView) v.findViewById(R.id.rvPerguntasDesafio);
        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
// Attach the layout manager to the recycler view
        rvPerguntasDesafio.setLayoutManager(gridLayoutManager);

//        ArrayList<PerguntasDesafio> perguntasDesafios = new ArrayList<>();
//        for (int i = 0; i < 50; i++){
//            PerguntasDesafio perguntasDesafio = new PerguntasDesafio();
//            perguntasDesafio.setCdPergunta(String.valueOf(i+1));
//            perguntasDesafio.setCdCategoria("Desafio");
//            perguntasDesafio.setDsPergunta("Ao final de uma corrida com 5 atletas, sabe-se" +
//                    "que Antonio chegou depois de Carlos. Ricardo e Jurandir chegaram ao mesmo" +
//                    "tempo. Dirceu chegou antes de Carlos. O corredor que ganhou, chegou sozinho." +
//                    "Quem ganhou a corrida?");
//            perguntasDesafio.setDsResposta1("Antonio");
//            perguntasDesafio.setDsResposta2("Carlos");
//            perguntasDesafio.setDsResposta3("Jurandir");
//            perguntasDesafio.setDsResposta4("Dirceu");
//            perguntasDesafio.setCorreta("Dirceu");
//            perguntasDesafios.add(perguntasDesafio);
//        }
//
//        AdapterPerguntasDesafio adapterPerguntasDesafio = new AdapterPerguntasDesafio(context, perguntasDesafios);
//        rvPerguntasDesafio.setAdapter(adapterPerguntasDesafio);
    }

    public void carregarPerguntas() {
        final ProgressDialog dialog = new ProgressDialog(context); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Carregando. Aguarde...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<RespostaServidor> call = service.getAllDesafio();

        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        if (response.body().isSucess()){
                            if (response.body().isSucess()) {
                                Gson gson = new Gson();
                                JsonArray json = response.body().getData();
                                for (JsonElement j : json) {
                                    PerguntasDesafio pd = gson.fromJson(j.toString(), PerguntasDesafio.class);
                                    perguntasDesafios.add(pd);
                                }

                                AdapterPerguntasDesafio adapterPerguntasDesafio = new AdapterPerguntasDesafio(context, perguntasDesafios);
                                rvPerguntasDesafio.setAdapter(adapterPerguntasDesafio);
                                cacheAplicativo.setPerguntasDesafios(perguntasDesafios);
                            } else{
                                Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RespostaServidor> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(context, "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }

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
