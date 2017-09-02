package com.example.kaua.businessgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tela_login extends AppCompatActivity {

    private Button btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        setView();
        setClick();
    }

    public void setView(){
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
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
