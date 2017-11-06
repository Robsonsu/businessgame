package com.example.kaua.businessgame;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.kaua.businessgame.Response.AcessarPartida;
import com.example.kaua.businessgame.Response.ResponseConectaLider;
import com.example.kaua.businessgame.Response.ResponseTokenPartida;
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

        ((tela_principal) context).toolbar.setVisibility(View.GONE);
        setView(view);
        setAcoesView();

        return view;
    }

    public void setView(View v){
        rgTpAcesso = (RadioGroup) v.findViewById(R.id.rgTpAcessoPartida);
        edtToken = (EditText) v.findViewById(R.id.edtTokenPartida);
        tilEquipe = (TextInputLayout) v.findViewById(R.id.tilEquipe);
        edtNmEquipe = (EditText) v.findViewById(R.id.edtNmEquipe);
        btnConectar = (Button) v.findViewById(R.id.btnConectarPartida);
    }

    public void setAcoesView(){
        rgTpAcesso.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.rbLider:
                        tilEquipe.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rbIntegrante:
                        tilEquipe.setVisibility(View.GONE);
                        break;
                }
            }
        });

        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (rgTpAcesso.getCheckedRadioButtonId()){
                    case R.id.rbLider:
                        conectaLider(
                                cacheAplicativo.getIdConectado(),
                                edtToken.getText().toString(),
                                edtNmEquipe.getText().toString()
                        );
                        break;
                    case R.id.rbIntegrante:
                        conectaIntegrante(
                                cacheAplicativo.getIdConectado(),
                                edtToken.getText().toString()
                        );
                        break;
                }
            }
        });
    }

    public void conectaLider(String id, String token, String nmEquipe) {
        final ProgressDialog dialog;
                    dialog = new ProgressDialog(context); // this = YourActivity
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setMessage("Carregando. Aguarde...");
                    dialog.setIndeterminate(true);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();

                    RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
                    Call<ResponseConectaLider> call = service.conectaLider(id, token, nmEquipe);

                    call.enqueue(new Callback<ResponseConectaLider>() {
                        @Override
                        public void onResponse(Call<ResponseConectaLider> call, Response<ResponseConectaLider> response) {
                            if (response.isSuccessful()) {
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                try {
                                    if (response.body().getSucess().equals("true")){
                                        String tk = response.body().getToken_equipe();
                                        cacheAplicativo.setTokenEquipe(tk);

                                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                        fragmentTransaction.replace(R.id.fl_principal, TelaToken.newInstance(true));
                                        fragmentTransaction.commit();
                                    } else {
                                        Toast.makeText(context, "Erro: ".concat(response.body().getMessage()), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                if (dialog.isShowing())
                        dialog.dismiss();
                    Toast.makeText(context, "Erro: ".concat(response.body().getMessage()), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ResponseConectaLider> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(context, "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void conectaIntegrante(String id, String token) {
        final ProgressDialog dialog;
        dialog = new ProgressDialog(context); // this = YourActivity
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Carregando. Aguarde...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);
        Call<ResponseTokenPartida> call = service.conectaJogador(id, token);

        call.enqueue(new Callback<ResponseTokenPartida>() {
            @Override
            public void onResponse(Call<ResponseTokenPartida> call, Response<ResponseTokenPartida> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        if (response.body().getSucess().equals("true")){
                            cacheAplicativo.setTokenpartida(response.body().getToken_partida());
//                            startActivity(new Intent(context, tela_tabuleiro.class));
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.fl_principal, new TelaTabuleiro());
                            transaction.commit();
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
            public void onFailure(Call<ResponseTokenPartida> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
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
