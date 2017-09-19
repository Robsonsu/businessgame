package com.example.kaua.businessgame;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tela_login extends AppCompatActivity {


    private Button btnCadastro;
    private Button btnEntrar;
    private EditText edtRA;
    private EditText edtSenha;
    private TextView tvEsqueciSenha;
    private ProgressDialog progress;
    RespostaServidor resposta = new RespostaServidor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);
        setView();
        setClick();

    }

    public void setView(){
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        edtRA = (EditText) findViewById(R.id.edtRA);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        tvEsqueciSenha = (TextView) findViewById(R.id.tvEsqueciSenha);
        btnCadastro = (Button) findViewById(R.id.btnCadastro);
    }

    public void setClick(){
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(tela_login.this, tela_principal.class));
//                retrofitConverter();
            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(tela_login.this, tela_cadastro.class));
            }
        });
    }

    public void retrofitConverter() {
        final String json  =  "[\n" +
                "{\n" +
                "\t\"login\":\"123456\",\n" +
                "\t\"senha\":\"123456\"\n" +
                "\t\n" +
                "}\n" +
                "]";
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);

        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<RespostaServidor> call = service.efetuarlogin(body);

        call.enqueue(new Callback<RespostaServidor>() {
            @Override
            public void onResponse(Call<RespostaServidor> call, Response<RespostaServidor> response) {

                if (response.isSuccessful()) {

                    RespostaServidor respostaServidor = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (respostaServidor != null) {

                        if(respostaServidor.isSucess()) {

                            resposta.setData(respostaServidor.getData());
                            resposta.setMessage(respostaServidor.getMessage());
                            resposta.setResult(respostaServidor.getResult());
                            resposta.setSucess(respostaServidor.isSucess());

                            progress.dismiss();
                            mostrarData(resposta.getMessage() + "\n" + resposta.getData() + "\n" + resposta.getResult());

                        } else{

                            Toast.makeText(getApplicationContext(),"Insira unidade e valores válidos", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(getApplicationContext(),"Resposta nula do servidor", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(),"Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
                    mostrarData(errorBody.toString());
                }

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<RespostaServidor> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void mostrarData(String message){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(tela_login.this);
        builder1.setMessage(message);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

//        builder1.setNegativeButton(
//                "No",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
