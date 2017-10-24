package com.example.kaua.businessgame;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.kaua.businessgame.Request.Cadastrar;
import com.example.kaua.businessgame.Response.RespostaServidor;
import com.google.gson.Gson;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaCadastro extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Context context;
    private EditText edtNome;
    private EditText edtSenha;
    private EditText edtRepetirSenha;
    private RadioGroup rgTpAcesso;
    private Button btnCriarConta;

    public TelaCadastro() {
        // Required empty public constructor
    }
    public static TelaCadastro newInstance() {
        return new TelaCadastro();
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
        View view = inflater.inflate(R.layout.fragment_tela_cadastro, container, false);

        setView(view);
        setAcoesView();

        return view;
    }

    public void setView(View view){
        edtNome = (EditText) view.findViewById(R.id.edtNomeCadastro);
        edtSenha = (EditText) view.findViewById(R.id.edtSenhaCadastro);
        edtRepetirSenha = (EditText) view.findViewById(R.id.edtConfirmarSenhaCadastro);
        rgTpAcesso = (RadioGroup) view.findViewById(R.id.rgTpAcesso);
        btnCriarConta = (Button) view.findViewById(R.id.btnConfirmarCadastro);
    }

    public void setAcoesView(){
        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarInfos();
            }
        });
    }

    public void validarInfos(){
        if (edtNome.getText().toString().equals("")){
            alertDialog("nome");
            edtNome.requestFocus();
            return;
        }

        if (edtSenha.getText().toString().equals("")){
            alertDialog("senha");
            edtSenha.requestFocus();
            return;
        }

        if (edtRepetirSenha.getText().toString().equals("")){
            alertDialog("senha novamente");
            edtRepetirSenha.requestFocus();
            return;
        }

        if (!edtSenha.getText().toString().equals(edtRepetirSenha.getText().toString())){
            alertDialog("erroSenha");
            edtRepetirSenha.requestFocus();
            return;
        }

        if (rgTpAcesso.getCheckedRadioButtonId() != R.id.RbtnProfessor && rgTpAcesso.getCheckedRadioButtonId() != R.id.RbtnAluno){
            alertDialog("erroAcesso");
            return;
        }

        String tpAcesso = (rgTpAcesso.getCheckedRadioButtonId() == R.id.RbtnProfessor ? "1" : "2");

        chamarCadastrar(edtNome.getText().toString(),
                edtSenha.getText().toString(),
                tpAcesso);
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

    public AlertDialog alertDialog(String erro){

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Ops!");

        switch (erro){
            case "erroAcesso":
                alertDialog.setMessage("Escolha o acesso de Professor ou Aluno para o cadastro.");
                break;
            case "erroSenha":
                alertDialog.setMessage("As senhas devem ser iguais.");
                break;
            default:
                alertDialog.setMessage("Insira " + erro + " para continuar.");
                break;
        }

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

        return alertDialog;
    }

    public void chamarCadastrar(String nome, String senha, String tpAcesso) {
        Cadastrar cadastrar = new Cadastrar();
        cadastrar.setNome(nome);
        cadastrar.setSenha(senha);
        cadastrar.setTpAcesso(tpAcesso);

        Gson gson = new Gson();
        String json = gson.toJson(cadastrar);
        json = "["+ json + "]";

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);

        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<RespostaServidor> call = service.cadastrar(body);

        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {

                if (response.isSuccessful()) {

                    RespostaServidor respostaServidor = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (respostaServidor != null) {

                        if(respostaServidor.isSucess()) {

//                            resposta.setData(respostaServidor.getData());
//                            resposta.setMessage(respostaServidor.getMessage());
//                            resposta.setResult(respostaServidor.getResult());
//                            resposta.setSucess(respostaServidor.isSucess());
//
//                            progress.dismiss();
//                            mostrarData(resposta.getMessage() + "\n" + resposta.getData() + "\n" + resposta.getResult());

                        } else{

                            Toast.makeText(context,"Insira unidade e valores válidos", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(context,"Resposta nula do servidor", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(context,"Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
//                    mostrarData(errorBody.toString());
                }

//                progress.dismiss();
            }

            @Override
            public void onFailure(Call<RespostaServidor> call, Throwable t) {

                Toast.makeText(context,"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
