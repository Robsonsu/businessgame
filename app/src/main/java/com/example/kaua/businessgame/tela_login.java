package com.example.kaua.businessgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class tela_login extends AppCompatActivity {

    private Button btnEntrar;
    private EditText edtRA;
    private EditText edtSenha;

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
    }

    public void setClick(){
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(tela_login.this, tela_principal.class));
            }
        });
    }
}
