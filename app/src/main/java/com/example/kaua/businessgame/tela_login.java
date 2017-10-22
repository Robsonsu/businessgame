package com.example.kaua.businessgame;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaua.businessgame.Request.EfetuarLogin;
import com.example.kaua.businessgame.Response.responseEfetuarLogin;
import com.google.gson.Gson;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tela_login extends AppCompatActivity {


    private TextView tvCadastro;
    private Button btnEntrar;
    private EditText edtRA;
    private EditText edtSenha;
    private TextView tvEsqueciSenha;
    private ProgressDialog progress;
    CacheTool cacheTool = new CacheTool();



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
        tvCadastro = (TextView) findViewById(R.id.tvCadastro);
    }

    public void setClick(){
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(tela_login.this, tela_principal.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("fragment", "tela_principal");
//                it.putExtras(bundle);
//                startActivity(it);
               retrofitConverter(edtRA.getText().toString(), edtSenha.getText().toString());
            }
        });

        tvCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(tela_login.this, tela_principal.class);
                Bundle bundle = new Bundle();
                bundle.putString("fragment", "TelaCadastro");

                it.putExtras(bundle);
                startActivity(it);
            }
        });
    }

    public void retrofitConverter(String login, String senha) {
        EfetuarLogin efetuarLogin = new EfetuarLogin();
        efetuarLogin.setLogin(login);
        efetuarLogin.setSenha(senha);

        Gson gson = new Gson();
        String json = gson.toJson(efetuarLogin);

//        final String json  =  "[\n" +
//                "{\n" +
//                "\t\"login\":\"123456\",\n" +
//                "\t\"senha\":\"123456\"\n" +
//                "\t\n" +
//                "}\n" +
//                "]";
        json = "["+json+"]";
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);

        RetrofitService service = ServiceGenerator.createService(RetrofitService.class);

        Call<responseEfetuarLogin> call = service.efetuarlogin(body);

        call.enqueue(new Callback<responseEfetuarLogin>() {

            @Override
            public void onResponse(Call<responseEfetuarLogin> call, Response<responseEfetuarLogin> response) {

                try
                {
                    //get your response....
                  //  response.body();
                    String json = response.body().sucess.toString();
                    if (json.equals("true")) {

                        //verifica aqui se o corpo da resposta não é nulo

                     responseEfetuarLogin respostaAPi = new responseEfetuarLogin(response.body().sucess, response.body().cd_usuario,
                                response.body().nome, response.body().sessao, response.body().message
                                );

                        cacheAplicativo.setResponseEfetuarLogin(response.body());

                               // progress.dismiss();
                                mostrarData(respostaAPi.message + "\n" );

                                Intent it = new Intent(tela_login.this, tela_principal.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("fragment", "TelaNovaPartida");
                                it.putExtras(bundle);
                                startActivity(it);
                    } else {
                        Toast.makeText(getApplicationContext(),"Resposta não foi sucesso", Toast.LENGTH_SHORT).show();
                        // segura os erros de requisição
                        ResponseBody errorBody = response.errorBody();
                        mostrarData(errorBody.toString());
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }



              //  progress.dismiss();
            }

            @Override
            public void onFailure(Call<responseEfetuarLogin> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), "Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
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
